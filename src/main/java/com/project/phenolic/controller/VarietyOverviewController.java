package com.project.phenolic.controller;

import com.project.phenolic.common.Result;
import com.project.phenolic.entity.VarietyOverview;
import com.project.phenolic.service.IVarietyOverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lhy
 * @since 2025-09-10
 */
@RestController
@RequestMapping("/varietyOverview")
public class VarietyOverviewController {

    @Autowired
    private IVarietyOverviewService varietyOverviewService;

    @PostMapping("queryList")
    public Result queryList(String queryParam) {
        List<VarietyOverview> list;
        
        if (queryParam == null || queryParam.trim().isEmpty()) {
            // 如果查询参数为空，返回所有数据
            list = varietyOverviewService.list();
        } else {
            // 对所有字符串字段进行模糊匹配
            list = varietyOverviewService.lambdaQuery()
                    .like(VarietyOverview::getProductName, queryParam)
                    .or()
                    .like(VarietyOverview::getChineseName, queryParam)
                    .or()
                    .like(VarietyOverview::getAbbreviation, queryParam)
                    .or()
                    .like(VarietyOverview::getSourceMaterial, queryParam)
                    .or()
                    .like(VarietyOverview::getOriginalPlant, queryParam)
                    .or()
                    .like(VarietyOverview::getFamily, queryParam)
                    .or()
                    .like(VarietyOverview::getProcessingTechnology, queryParam)
                    .or()
                    .like(VarietyOverview::getCategory, queryParam)
                    .or()
                    .like(VarietyOverview::getBatches, queryParam)
                    .list();
        }

        return Result.success(list);
    }

}
