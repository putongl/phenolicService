package com.project.phenolic.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.phenolic.common.Result;
import com.project.phenolic.entity.ContentInfo;
import com.project.phenolic.entity.ContentSampleData;
import com.project.phenolic.service.IContentInfoService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lhy
 * @since 2025-10-11
 */
@Slf4j
@RestController
@RequestMapping("/contentInfo")
public class ContentInfoController {

    @Autowired
    private IContentInfoService contentInfoService;


    @PostMapping("/updateType")
    public Result updateType() {
        List<ContentInfo> list = contentInfoService.list();
        for (ContentInfo contentInfo : list) {
            String name = contentInfo.getName();
            String type = name.split("-")[0];

            // 1. 构建更新条件：以name为唯一标识
            ContentInfo query = new ContentInfo();
            query.setName(name); // 用name作为查询条件

            // 2. 构建更新内容：只设置需要更新的type字段
            ContentInfo update = new ContentInfo();
            update.setType(type);

            contentInfoService.update(update, new QueryWrapper<>(query));

        }

        return Result.success("更新成功");
    }

    @PostMapping("/queryRange")
    public Result queryRange(@RequestParam("type") String type, @RequestParam("phenolic") String phenolic) {
        List<ContentInfo> list = contentInfoService.lambdaQuery().eq(ContentInfo::getType, type).list();

        ArrayList<Double> contentList = new ArrayList<>();

        for (ContentInfo contentInfo : list) {
            switch (phenolic.toUpperCase()) {
                case "GAL":
                    contentList.add(contentInfo.getGal());
                    break;
                case "1-CQA":
                    contentList.add(contentInfo.getCqa1());
                    break;
                case "5-CQA":
                    contentList.add(contentInfo.getCqa5());
                    break;
                case "3-CQA":
                    contentList.add(contentInfo.getCqa3());
                    break;
                case "4-CQA":
                    contentList.add(contentInfo.getCqa4());
                    break;
                case "CAF":
                    contentList.add(contentInfo.getCaf());
                    break;
                case "SYR":
                    contentList.add(contentInfo.getSyr());
                    break;
                case "1,3-DQA":
                    contentList.add(contentInfo.getDqa13());
                    break;
                case "P-COU":
                    contentList.add(contentInfo.getPcou());
                    break;
                case "RUT":
                    contentList.add(contentInfo.getRut());
                    break;
                case "HYP":
                    contentList.add(contentInfo.getHyp());
                    break;
                case "ISO":
                    contentList.add(contentInfo.getIso());
                    break;
                case "LUT":
                    contentList.add(contentInfo.getLut());
                    break;
                case "3,4-DQA":
                    contentList.add(contentInfo.getDqa34());
                    break;
                case "QUE":
                    contentList.add(contentInfo.getQue());
                    break;
                case "3,5-DQA":
                    contentList.add(contentInfo.getDqa35());
                    break;
                case "4,5-DQA":
                    contentList.add(contentInfo.getDqa45());
                    break;
            }

        }

        // 获取最大值和最小值
        double min = Collections.min(contentList);
        double max = Collections.max(contentList);

        // 保留两位小数并格式化
        DecimalFormat df = new DecimalFormat("#0.00");
        String result = df.format(min) + "-" + df.format(max);

        return Result.success(result);
    }

    @PostMapping("/generateImage")
    public Result generateImage(@RequestParam("type") String type) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<ContentInfo> list = contentInfoService.lambdaQuery().eq(ContentInfo::getType, type).list();

            List<Double> glaList = new ArrayList<>();
            List<Double> cqa1List = new ArrayList<>();
            List<Double> cqa5List = new ArrayList<>();
            List<Double> cqa3List = new ArrayList<>();
            List<Double> cqa4List = new ArrayList<>();
            List<Double> cafList = new ArrayList<>();
            List<Double> syrList = new ArrayList<>();
            List<Double> dqa13List = new ArrayList<>();
            List<Double> pcouList = new ArrayList<>();
            List<Double> rutList = new ArrayList<>();
            List<Double> hypList = new ArrayList<>();
            List<Double> isoList = new ArrayList<>();
            List<Double> lutList = new ArrayList<>();
            List<Double> dqa34List = new ArrayList<>();
            List<Double> queList = new ArrayList<>();
            List<Double> dqa35List = new ArrayList<>();
            List<Double> dqa45List = new ArrayList<>();
            for (ContentInfo contentInfo : list) {
                glaList.add(contentInfo.getGal());
                cqa1List.add(contentInfo.getCqa1());
                cqa5List.add(contentInfo.getCqa5());
                cqa3List.add(contentInfo.getCqa3());
                cqa4List.add(contentInfo.getCqa4());
                cafList.add(contentInfo.getCaf());
                syrList.add(contentInfo.getSyr());
                dqa13List.add(contentInfo.getDqa13());
                pcouList.add(contentInfo.getPcou());
                rutList.add(contentInfo.getRut());
                hypList.add(contentInfo.getHyp());
                isoList.add(contentInfo.getIso());
                lutList.add(contentInfo.getLut());
                dqa34List.add(contentInfo.getDqa34());
                queList.add(contentInfo.getQue());
                dqa35List.add(contentInfo.getDqa35());
                dqa45List.add(contentInfo.getDqa45());
            }

            // 如果集合为空，则跳过
            // 不为空的集合进行计算
            if (!glaList.isEmpty()) {
                Map<String, Object> data = getCalResult(glaList);
                result.put("gal", data);
            }
            if (!cqa1List.isEmpty()) {
                Map<String, Object> data = getCalResult(cqa1List);
                result.put("cqa1", data);
            }
            if (!cqa5List.isEmpty()) {
                Map<String, Object> data = getCalResult(cqa5List);
                result.put("cqa5", data);
            }
            if (!cqa3List.isEmpty()) {
                Map<String, Object> data = getCalResult(cqa3List);
                result.put("cqa3", data);
            }
            if (!cqa4List.isEmpty()) {
                Map<String, Object> data = getCalResult(cqa4List);
                result.put("cqa4", data);
            }
            if (!cafList.isEmpty()) {
                Map<String, Object> data = getCalResult(cafList);
                result.put("caf", data);
            }
            if (!syrList.isEmpty()) {
                Map<String, Object> data = getCalResult(syrList);
                result.put("syr", data);
            }
            if (!dqa13List.isEmpty()) {
                Map<String, Object> data = getCalResult(dqa13List);
                result.put("dqa13", data);
            }
            if (!pcouList.isEmpty()) {
                Map<String, Object> data = getCalResult(pcouList);
                result.put("pcou", data);
            }
            if (!rutList.isEmpty()) {
                Map<String, Object> data = getCalResult(rutList);
                result.put("rut", data);
            }
            if (!hypList.isEmpty()) {
                Map<String, Object> data = getCalResult(hypList);
                result.put("hyp", data);
            }
            if (!isoList.isEmpty()) {
                Map<String, Object> data = getCalResult(isoList);
                result.put("iso", data);
            }
            if (!lutList.isEmpty()) {
                Map<String, Object> data = getCalResult(lutList);
                result.put("lut", data);
            }
            if (!dqa34List.isEmpty()) {
                Map<String, Object> data = getCalResult(dqa34List);
                result.put("dqa34", data);
            }
            if (!queList.isEmpty()) {
                Map<String, Object> data = getCalResult(queList);
                result.put("que", data);
            }
            if (!dqa35List.isEmpty()) {
                Map<String, Object> data = getCalResult(dqa35List);
                result.put("dqa35", data);
            }
            if (!dqa45List.isEmpty()) {
                Map<String, Object> data = getCalResult(dqa45List);
                result.put("dqa45", data);
            }

        } catch (Exception e) {
            log.error("生成数据失败", e);
            return Result.fail("生成数据失败" + e);
        }

        return Result.success(result);
    }

    private static Map<String, Object> getCalResult(List<Double> list) {
        Collections.sort(list);
        int n = list.size();

        // 计算最小值、最大值
        double minGla = list.get(0);
        double maxGla = list.get(n - 1);

        // 处理只有1条数据的情况
        if (n == 1) {
            Map<String, Object> data = new HashMap<>();
            data.put("min", minGla);
            data.put("q1", minGla); // 只有1条数据时，Q1、中位数、Q3都等于该值
            data.put("mid", minGla);
            data.put("q3", minGla);
            data.put("max", maxGla);
            data.put("iqr", 0.0); // 四分位距为0
            data.put("lowerBound", minGla); // 边界值也设为该值
            data.put("upperBound", maxGla);
            return data;
        }

        // 计算中位数（midGla = Q2）
        double midGla;
        if (n % 2 == 1) {
            midGla = list.get(n / 2);
        } else {
            midGla = (list.get(n / 2 - 1) + list.get(n / 2)) / 2.0;
        }

        // 计算下四分位数（Q1）和上四分位数（Q3）
        double q1, q3;
        List<Double> lowerHalf, upperHalf;

        if (n % 2 == 1) {
            // 奇数个数据：中位数单独拆分，上下两部分不包含中位数
            lowerHalf = list.subList(0, n / 2); // 前半部分（0到中间索引-1）
            upperHalf = list.subList(n / 2 + 1, n); // 后半部分（中间索引+1到末尾）
        } else {
            // 偶数个数据：上下两部分各占一半
            lowerHalf = list.subList(0, n / 2); // 前半部分（0到n/2 -1）
            upperHalf = list.subList(n / 2, n); // 后半部分（n/2到末尾）
        }

        // 计算Q1（下四分位数：下部分的中位数）
        int lowerSize = lowerHalf.size();
        if (lowerSize % 2 == 1) {
            q1 = lowerHalf.get(lowerSize / 2);
        } else {
            q1 = (lowerHalf.get(lowerSize / 2 - 1) + lowerHalf.get(lowerSize / 2)) / 2.0;
        }

        // 计算Q3（上四分位数：上部分的中位数）
        int upperSize = upperHalf.size();
        if (upperSize % 2 == 1) {
            q3 = upperHalf.get(upperSize / 2);
        } else {
            q3 = (upperHalf.get(upperSize / 2 - 1) + upperHalf.get(upperSize / 2)) / 2.0;
        }

        // 计算四分位距（IQR）和异常值边界
        double iqr = q3 - q1;
        double lowerBound = q1 - 1.5 * iqr; // 下限（小于此值为异常值）
        double upperBound = q3 + 1.5 * iqr; // 上限（大于此值为异常值）

        Map<String, Object> data = new HashMap<>();
        data.put("min", minGla);
        data.put("q1", q1);
        data.put("mid", midGla);
        data.put("q3", q3);
        data.put("max", maxGla);
        data.put("iqr", iqr);
        data.put("lowerBound", lowerBound);
        data.put("upperBound", upperBound);
        return data;
    }

}