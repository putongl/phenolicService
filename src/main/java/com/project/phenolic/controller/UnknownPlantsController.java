package com.project.phenolic.controller;

import com.project.phenolic.common.Result;
import com.project.phenolic.service.IUnknownPlantsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lhy
 * @since 2025-08-22
 */
@Slf4j
@RestController
@RequestMapping("/unknownPlants")
public class UnknownPlantsController {

    @Autowired
    private IUnknownPlantsService unknownPlantsService;

    /**
     *  批量导入
     */
    @PostMapping("/batchSaveImportData")
    public Result batchSaveImportData(@RequestParam("file") MultipartFile file, @RequestParam(value = "requestParams",required = false) Map<String, String> requestParams) {

        log.info("收到自定义映射Excel导入请求，文件名：{}", file.getOriginalFilename());

        try {
            // 调用服务层处理导入
            Result result = unknownPlantsService.batchSaveImportData(file, requestParams);

            return result;

        } catch (Exception e) {
            log.error("导入Excel文件失败", e);
            return Result.fail("导入失败：" + e.getMessage());
        }
    }

}
