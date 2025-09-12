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
    public Result queryList() {

        List<VarietyOverview> list = varietyOverviewService.lambdaQuery().list();

        return Result.success(list);
    }

}
