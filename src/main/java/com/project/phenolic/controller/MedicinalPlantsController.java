package com.project.phenolic.controller;

import com.project.phenolic.common.Result;
import com.project.phenolic.entity.MedicinalPlants;
import com.project.phenolic.entity.UnknownPlants;
import com.project.phenolic.service.IMedicinalPlantsService;
import com.project.phenolic.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lhy
 * @since 2025-07-21
 */
@Slf4j
@RestController
@RequestMapping("/medicinalPlants")
public class MedicinalPlantsController {

    @Autowired
    private IMedicinalPlantsService medicinalPlantsService;

    /**
     * 使用自定义字段映射导入Excel
     */
    @PostMapping("/importExcelWithMapping")
    public Result importExcelWithMapping(@RequestParam("file") MultipartFile file) {
        log.info("收到自定义映射Excel导入请求，文件名：{}", file.getOriginalFilename());

        try {
            // 调用服务层处理导入
            Map<String, Object> importResult = medicinalPlantsService.importExcelWithMapping(file);

            if ((Boolean) importResult.get("success")) {
                log.info("Excel导入成功，成功：{} 条，失败：{} 条",
                    importResult.get("successCount"), importResult.get("errorCount"));
                return Result.success(importResult);
            } else {
                log.warn("Excel导入失败：{}", importResult.get("message"));
                return Result.fail((String) importResult.get("message"));
            }

        } catch (Exception e) {
            log.error("导入Excel文件失败", e);
            return Result.fail("导入失败：" + e.getMessage());
        }
    }

    /**
     * 获取支持的Excel列名列表
     */
    @GetMapping("/getSupportedColumns")
    public Result getSupportedColumns() {
        log.info("获取支持的Excel列名列表");

        try {
            List<String> supportedColumns = medicinalPlantsService.getSupportedExcelColumns();
            return Result.success(supportedColumns);

        } catch (Exception e) {
            log.error("获取支持的列名失败", e);
            return Result.fail("获取失败：" + e.getMessage());
        }
    }

    /**
     * 下载模板
     */
    @GetMapping("/downloadTemplate")
    public void downloadExcelTemplate(HttpServletResponse response) {
        // 1. 定义文件名（不含后缀）
        String fileName = "Unknown Sample Template";

        // 3. 调用工具方法下载模板
        ExcelUtils.downloadTemplate(response, fileName, UnknownPlants.class,  null);
    }

    /**
     * 获取类型
     */
    @GetMapping("/getType")
    public Result getType() {
        log.info("获取类型");

        try {
            List<MedicinalPlants> type = medicinalPlantsService.lambdaQuery()
                    .select(MedicinalPlants::getType)
                    .groupBy(MedicinalPlants::getType)
                    .list();
            List<String> list = type.stream().map(MedicinalPlants::getType).collect(Collectors.toList());

            return Result.success(list);

        } catch (Exception e) {
            log.error("获取类型失败", e);
            return Result.fail("获取失败：" + e.getMessage());
        }
    }
}
