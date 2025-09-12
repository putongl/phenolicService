package com.project.phenolic.controller;

import com.project.phenolic.common.Result;
import com.project.phenolic.entity.VarietyDetail;
import com.project.phenolic.service.IVarietyDetailService;
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
@RequestMapping("/varietyDetail")
public class VarietyDetailController {

    @Autowired
    private IVarietyDetailService varietyDetailService;

    @PostMapping("queryList")
    public Result queryList(String type) {
        // 将type中的空格去掉，例如 "Ai DC" -> "AiDC"
        String sampleNamePrefix = type.replaceAll("\\s+", "");
        
        // 查询sampleName以该前缀开头的记录，例如 AiDC-1, AiDC-2
        List<VarietyDetail> list = varietyDetailService.lambdaQuery()
                .likeRight(VarietyDetail::getSampleName, sampleNamePrefix + "-")
                .list();

        return Result.success(list);
    }

}
