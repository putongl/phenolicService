package com.project.phenolic.controller;

import com.project.phenolic.entity.Similarity;
import com.project.phenolic.service.ISimilarityService;
import com.project.phenolic.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lhy
 * @since 2025-09-06
 */
@RestController
@RequestMapping("/similarity")
public class SimilarityController {

    @Autowired
    private ISimilarityService similarityService;

    /**
     * 导出数据
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, String batch) {
        // 从数据库中查询数据
        List<Similarity> list = similarityService.lambdaQuery()
                .eq(Similarity::getBatch, batch).list();

        ExcelUtils.exportExcel(response, "相似度数据", Similarity.class, list, "相似度数据");


    }

}
