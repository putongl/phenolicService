package com.project.phenolic.controller;

import com.project.phenolic.common.Result;
import com.project.phenolic.entity.UnknownPlants;
import com.project.phenolic.service.IUnknownPlantsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    public Result batchSaveImportData(@RequestParam("file") MultipartFile file, @RequestParam(value = "type",required = false) String type) {

        log.info("收到自定义映射Excel导入请求，文件名：{}", file.getOriginalFilename());

        try {
            // 调用服务层处理导入
            Result result = unknownPlantsService.batchSaveImportData(file, type);

            return result;

        } catch (Exception e) {
            log.error("导入Excel文件失败", e);
            return Result.fail("导入失败：" + e.getMessage());
        }
    }

    /**
     * 查询批次导入数据
     */
    @PostMapping("/getBatchData")
    public Result getBatchData(@RequestBody Map<String,Object> queryMap) {
        log.info("收到查询批次导入数据请求");
        String queryId = queryMap.get("queryId").toString();
        Double similarityThreshold = queryMap.get("similarityThreshold") != null ? 
            Double.parseDouble(queryMap.get("similarityThreshold").toString()) : null;
        Integer topMatchNumber = queryMap.get("topMatchNumber") != null ? 
            Integer.parseInt(queryMap.get("topMatchNumber").toString()) : null;

        try {
            // 调用服务层处理导入
            List<UnknownPlants> list = unknownPlantsService.lambdaQuery().eq(UnknownPlants::getBatch, queryId)
                    .list();

            // 正则表达式匹配括号中的相似度值
            Pattern pattern = Pattern.compile("\\((\\d+\\.\\d+)\\)");

            for (UnknownPlants unknownPlants : list){
                // 收集所有top字段的相似度值
                List<TopMatch> topMatches = new ArrayList<>();
                
                // 处理top1到top6
                String[] topFields = {unknownPlants.getTop1(), unknownPlants.getTop2(), 
                                    unknownPlants.getTop3(), unknownPlants.getTop4(), 
                                    unknownPlants.getTop5(), unknownPlants.getTop6()};
                
                for (int i = 0; i < topFields.length; i++) {
                    String topField = topFields[i];
                    if (topField != null && !topField.trim().isEmpty()) {
                        Matcher matcher = pattern.matcher(topField);
                        if (matcher.find()) {
                            double similarity = Double.parseDouble(matcher.group(1));
                            // 如果设置了相似度阈值，进行过滤
                            if (similarityThreshold == null || similarity > similarityThreshold) {
                                topMatches.add(new TopMatch(topField, similarity, i + 1));
                            }
                        }
                    }
                }
                
                // 按相似度降序排序
                topMatches.sort((a, b) -> Double.compare(b.similarity, a.similarity));
                
                // 根据topMatchNumber限制返回数量
                if (topMatchNumber != null && topMatches.size() > topMatchNumber) {
                    topMatches = topMatches.subList(0, topMatchNumber);
                }
                
                // 将结果用逗号分隔存入top字段
                String topResult = topMatches.stream()
                    .map(tm -> tm.originalValue)
                    .collect(Collectors.joining(","));
                
                unknownPlants.setTop(topResult);
            }

            return Result.success(list);

        } catch (Exception e) {
            log.error("查询批次导入数据失败", e);
            return Result.fail("查询批次导入数据失败：" + e.getMessage());
        }
    }
    
    /**
     * 内部类用于存储top匹配信息
     */
    private static class TopMatch {
        String originalValue;
        double similarity;
        int topIndex;
        
        TopMatch(String originalValue, double similarity, int topIndex) {
            this.originalValue = originalValue;
            this.similarity = similarity;
            this.topIndex = topIndex;
        }
    }


}
