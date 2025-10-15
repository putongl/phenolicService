package com.project.phenolic.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.project.phenolic.common.Result;
import com.project.phenolic.entity.ContentSampleData;
import com.project.phenolic.entity.Similarity;
import com.project.phenolic.entity.UnknownContent;
import com.project.phenolic.entity.UnknownPlants;
import com.project.phenolic.service.IUnknownContentService;
import com.project.phenolic.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lhy
 * @since 2025-10-14
 */
@Slf4j
@RestController
@RequestMapping("/unknownContent")
public class UnknownContentController {

    @Autowired
    private IUnknownContentService unknownContentService;

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/import")
    public Result importData(@RequestParam("file") MultipartFile file) {
        List<UnknownContent> results = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        String queryId = UUID.randomUUID().toString().replace("-", "");
        List<String> sheetNames = new ArrayList<>(); // 存储所有sheet名称

        // 第一步：单独获取所有sheet名称（仅读流一次，获取sheet信息后关闭流）
        try (InputStream inputStream = file.getInputStream()) {
            // 显式指定Excel格式（xlsx或xls，根据你的文件类型选择，这里用xlsx）
            try (ExcelReader reader = EasyExcel.read(inputStream)
                    .excelType(com.alibaba.excel.support.ExcelTypeEnum.XLSX) // 关键：指定格式
                    .build()) {
                List<ReadSheet> sheets = reader.excelExecutor().sheetList();
                // 提取所有sheet名称，不保存reader（避免流被占用）
                for (ReadSheet sheet : sheets) {
                    sheetNames.add(sheet.getSheetName());
                    log.info("获取到sheet：{}", sheet.getSheetName());
                }
            }
        } catch (Exception e) {
            log.error("获取Excel sheet列表失败", e);
            throw new RuntimeException("读取Excel结构失败：" + e.getMessage(), e);
        }

        // 第二步：遍历每个sheet名称，重新获取输入流读取数据
        for (String sheetName : sheetNames) {
            // 每次读一个sheet，都新创建输入流（关键：避免流重复使用）
            try (InputStream inputStream = file.getInputStream()) {
                // 读取当前sheet数据，显式指定格式和head
                List<ContentSampleData> data = EasyExcel.read(inputStream)
                        .excelType(com.alibaba.excel.support.ExcelTypeEnum.XLSX) // 再次指定格式
                        .head(ContentSampleData.class) // 绑定实体类
                        .sheet(sheetName) // 指定要读的sheet
                        .doReadSync(); // 同步读取

                log.info("读取sheet [{}] 成功，数据条数：{}", sheetName, data.size());
                System.out.println(data);

                // 计算并封装数据
                UnknownContent info = calculateContent(data, sheetName);
                info.setBatch(queryId);
                results.add(info);

            } catch (Exception e) {
                log.error("读取sheet [{}] 失败", sheetName, e);
                throw new RuntimeException("读取sheet [" + sheetName + "] 失败：" + e.getMessage(), e);
            }
        }

        // 第三步：批量保存数据
        saveToDatabase(results);
        map.put("results", results);
        map.put("batch", queryId);
        return Result.success(map);
    }


    private UnknownContent calculateContent(List<ContentSampleData> dataList, String sampleName) {
        UnknownContent info = new UnknownContent();
        info.setName(sampleName);

        for (ContentSampleData data : dataList) {
            if (data.getArea() == null || data.getVolume() == null || data.getMass() == null)
                continue;

            switch (data.getShortName().toUpperCase()) {
                case "GAL":
                    info.setGal(data.getVolume() * data.getArea() / 124672300.4069 / data.getMass() / 1000 * 100 * 10000);
                    break;
                case "1-CQA":
                    info.setCqa1(data.getVolume() * data.getArea() / 43904505.520185 / data.getMass() / 1000 * 100 * 10000);
                    break;
                case "5-CQA":
                    info.setCqa5(data.getVolume() * data.getArea() / 46889859.701983 / data.getMass() / 1000 * 100 * 10000);
                    break;
                case "3-CQA":
                    info.setCqa3(data.getVolume() * data.getArea() / 48629727.642447 / data.getMass() / 1000 * 100 * 10000);
                    break;
                case "4-CQA":
                    info.setCqa4(data.getVolume() * data.getArea() / 48516671.290083 / data.getMass() / 1000 * 100 * 10000);
                    break;
                case "CAF":
                    info.setCaf(data.getVolume() * data.getArea() / 83754692.334035 / data.getMass() / 1000 * 100 * 10000);
                    break;
                case "SYR":
                    info.setSyr(data.getVolume() * data.getArea() / 113213957.13 / data.getMass() / 1000 * 100 * 10000);
                    break;
                case "1,3-DQA":
                    info.setDqa13(data.getVolume() * data.getArea() / 48896559.211812 / data.getMass() / 1000 * 100 * 10000);
                    break;
                case "P-COU":
                    info.setPcou(data.getVolume() * data.getArea() / 69685643.641915 / data.getMass() / 1000 * 100 * 10000);
                    break;
                case "RUT":
                    info.setRut(data.getVolume() * data.getArea() / 21290592.122034 / data.getMass() / 1000 * 100 * 10000);
                    break;
                case "HYP":
                    info.setHyp(data.getVolume() * data.getArea() / 27329797.596531 / data.getMass() / 1000 * 100 * 10000);
                    break;
                case "ISO":
                    info.setIso(data.getVolume() * data.getArea() / 28306518.764197 / data.getMass() / 1000 * 100 * 10000);
                    break;
                case "LUT":
                    info.setLut(data.getVolume() * data.getArea() / 33374856.454489 / data.getMass() / 1000 * 100 * 10000);
                    break;
                case "3,4-DQA":
                    info.setDqa34(data.getVolume() * data.getArea() / 59441551.90071 / data.getMass() / 1000 * 100 * 10000);
                    break;
                case "QUE":
                    info.setQue(data.getVolume() * data.getArea() / 29220434.876873 / data.getMass() / 1000 * 100 * 10000);
                    break;
                case "3,5-DQA":
                    info.setDqa35(data.getVolume() * data.getArea() / 63732877.875838 / data.getMass() / 1000 * 100 * 10000);
                    break;
                case "4,5-DQA":
                    info.setDqa45(data.getVolume() * data.getArea() / 62643328.429183 / data.getMass() / 1000 * 100 * 10000);
                    break;
            }
        }

        return info;
    }

    private void saveToDatabase(List<UnknownContent> list) {
        list.forEach(System.out::println);

        // 1. 前置校验：避免空集合或空数据
        if (list == null || list.isEmpty()) {
            log.warn("待保存的数据集为空，无需执行保存操作");
            return;
        }

        try {
            // 执行批量保存
            boolean success = unknownContentService.saveBatch(list);

            // 校验当前批次保存结果
            if (!success) {
                log.error("数据保存失败");
                throw new RuntimeException("数据保存失败");
            }

        } catch (Exception e) {
            log.error("批量保存数据失败，已触发事务回滚，错误原因：", e);
            // 向外抛出异常（可选，根据上层逻辑是否需要捕获）
            throw new RuntimeException("数据保存失败：" + e.getMessage(), e);
        }
    }


    /**
     * 下载模板
     */
    @GetMapping("/downloadTemplate")
    public void downloadExcelTemplate(HttpServletResponse response) {
        // 1. 定义文件名（不含后缀）
        String fileName = "未知含量模板";

        List<ContentSampleData> dataList = createTemplateData();

        // 3. 调用工具方法下载模板
        ExcelUtils.downloadTemplate(response, fileName, ContentSampleData.class,  dataList);
    }


    /**
     * 导出数据
     */
    @PostMapping("/exportData")
    public void export(HttpServletResponse response, String batch) {
        // 从数据库中查询数据
        List<UnknownContent> list = unknownContentService.lambdaQuery()
                .eq(UnknownContent::getBatch, batch).list();

        ExcelUtils.exportExcel(response, "未知含量样本数据", UnknownContent.class, list, "未知含量样本数据");

    }


    /**
     * 创建固定的模板数据
     * @return 模板数据列表
     */
    private static List<ContentSampleData> createTemplateData() {
        List<ContentSampleData> list = new ArrayList<>();

        // 添加固定的化合物数据
        list.add(createData("Gallic acid", "GAL"));
        list.add(createData("1-Caffeoylquinic acid", "1-CQA"));
        list.add(createData("5-Caffeoylquinic acid", "5-CQA"));
        list.add(createData("3-Caffeoylquinic acid", "3-CQA"));
        list.add(createData("4-Caffeoylquinic acid", "4-CQA"));
        list.add(createData("Caffeic acid", "CAF"));
        list.add(createData("Syringic acid", "SYR"));
        list.add(createData("1,3-dicaffeoylquinic acid", "1,3-DQA"));
        list.add(createData("p-coumaric acid", "p-COU"));
        list.add(createData("Rutin", "RUT"));
        list.add(createData("Hyperoside", "HYP"));
        list.add(createData("Isoquercitrin", "ISO"));
        list.add(createData("Luteoloside", "LUT"));
        list.add(createData("3,4-dicaffeoylquinic acid", "3,4-DQA"));
        list.add(createData("Quercitrin", "QUE"));
        list.add(createData("3,5-dicaffeylquinic acid", "3,5-DQA"));
        list.add(createData("4,5-dicaffeoylquinic acid", "4,5-DQA"));

        return list;
    }

    /**
     * 创建单条数据
     * @param compound 化合物名称
     * @param shortName 缩写
     * @return ContentSampleData对象
     */
    private static ContentSampleData createData(String compound, String shortName) {
        ContentSampleData data = new ContentSampleData();
        data.setCompound(compound);
        data.setShortName(shortName);
        // mass, volume, area 保持为null，导出时显示为空
        return data;
    }


}
