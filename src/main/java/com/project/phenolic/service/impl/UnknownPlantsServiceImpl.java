package com.project.phenolic.service.impl;

import com.project.phenolic.common.Result;
import com.project.phenolic.config.ExcelFieldMapping;
import com.project.phenolic.entity.MedicinalPlants;
import com.project.phenolic.entity.UnknownPlants;
import com.project.phenolic.mapper.UnknownPlantsMapper;
import com.project.phenolic.service.IMedicinalPlantsService;
import com.project.phenolic.service.IUnknownPlantsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.phenolic.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhy
 * @since 2025-08-22
 */
@Slf4j
@Service
public class UnknownPlantsServiceImpl extends ServiceImpl<UnknownPlantsMapper, UnknownPlants> implements IUnknownPlantsService {

    @Autowired
    private ExcelFieldMapping excelFieldMapping;

    @Autowired
    private IMedicinalPlantsService medicinalPlantsService;

    @Override
    public Result batchSaveImportData(MultipartFile file, String type) {
        log.info("开始使用自定义映射导入Excel文件：{}", file.getOriginalFilename());

        String queryId = UUID.randomUUID().toString().replace("-", "");

        // 验证文件
        String validationError = ExcelUtils.validateExcelFile(file);
        if (validationError != null) {
            return Result.fail("验证文件失败，上传文件需excel文件");
        }
        try {
            // 使用自定义映射读取Excel
            Map<String, Object> readResult = ExcelUtils.unknowWithMapping(file, excelFieldMapping);

            if (!(Boolean) readResult.get("success")) {
                return Result.fail("读取Excel文件失败");
            }

            // 获取成功读取的数据
            @SuppressWarnings("unchecked")
            List<UnknownPlants> importDataList =
                    (List<UnknownPlants>) readResult.get("successData");

            if (importDataList.isEmpty()) {
                return Result.fail("Excel文件中没有有效数据");
            }

            List<MedicinalPlants> medicinalPlantsList = new ArrayList<>();
            // 计算相似度
            if (type != null && !type.equals("all")) {
                medicinalPlantsList = medicinalPlantsService.lambdaQuery().eq(type != null ,MedicinalPlants::getType, type).list();

            }else {
                medicinalPlantsList = medicinalPlantsService.list();
            }

            // 计算余弦相似度并找出最相似的药用植物
            calculateCosineSimilarity(importDataList, medicinalPlantsList);

            importDataList.forEach(dto -> {
                dto.setType(dto.getTop1().split("-")[0]);
                dto.setBatch(queryId);
            });

            // 批量保存数据
            Map<String, Object> saveResult = batchSave(importDataList);

            // 合并结果
            Map<String, Object> finalResult = new java.util.HashMap<>();
            finalResult.put("success", (Boolean) saveResult.get("success"));
            finalResult.put("message", saveResult.get("message"));
            finalResult.put("totalCount", readResult.get("totalCount"));
            finalResult.put("successCount", saveResult.get("successCount"));
            finalResult.put("errorCount", readResult.get("errorCount"));
            finalResult.put("readErrorMessages", readResult.get("errorMessages"));
            finalResult.put("saveErrorMessages", saveResult.get("errorMessages"));
            finalResult.put("queryId", queryId);

            return Result.success(finalResult);

        } catch (Exception e) {
            log.error("导入Excel文件失败", e);
            return Result.fail("导入Excel文件失败");
        }

    }

    @Override
    public Map<String, Object> batchSave(List<UnknownPlants> importDataList) {
        log.info("开始批量保存导入数据，共 {} 条", importDataList.size());

        Map<String, Object> result = new java.util.HashMap<>();
        List<String> errorMessages = new ArrayList<>();
        int successCount = 0;

        try {
            List<UnknownPlants> entityList = new ArrayList<>();

            for (int i = 0; i < importDataList.size(); i++) {
                UnknownPlants unknownPlants = importDataList.get(i);

                try {
                    // 转换为实体对象
                    entityList.add(unknownPlants);
                    successCount++;

                } catch (Exception e) {
                    String errorMsg = String.format("第%d条数据转换失败：%s", i + 1, e.getMessage());
                    log.error(errorMsg, e);
                    errorMessages.add(errorMsg);
                }
            }


            // 批量保存
            if (!entityList.isEmpty()) {
                boolean saveSuccess = this.saveBatch(entityList);
                if (!saveSuccess) {
                    result.put("success", false);
                    result.put("message", "数据库保存失败");
                    result.put("successCount", 0);
                    result.put("errorMessages", Arrays.asList("批量保存到数据库失败"));
                    return result;
                }
            }

            result.put("success", true);
            result.put("message", "数据保存完成");
            result.put("successCount", successCount);
            result.put("errorMessages", errorMessages);

            log.info("批量保存完成，成功：{} 条，失败：{} 条", successCount, errorMessages.size());

        } catch (Exception e) {
            log.error("批量保存数据失败", e);
            result.put("success", false);
            result.put("message", "保存失败：" + e.getMessage());
            result.put("successCount", 0);
            result.put("errorMessages", Arrays.asList("保存过程中发生异常：" + e.getMessage()));
        }

        return result;
    }

    @Override
    public List<String> getSupportedExcelColumns() {
        return new ArrayList<>(excelFieldMapping.getSupportedExcelColumns());
    }

    /**
     * 计算余弦相似度
     * @param importDataList 未知植物数据列表
     * @param medicinalPlantsList 药用植物数据列表
     */
    private void calculateCosineSimilarity(List<UnknownPlants> importDataList, List<MedicinalPlants> medicinalPlantsList) {
        log.info("开始计算余弦相似度，未知植物数量：{}，药用植物数量：{}", importDataList.size(), medicinalPlantsList.size());
        
        for (int i = 0; i < importDataList.size(); i++) {
            UnknownPlants unknownPlant = importDataList.get(i);
            double[] x = extractTimeVector(unknownPlant);
            
            double maxSimilarity = -1;
            String[] topSimilar = new String[6]; // top1-top6
            double[] topSimilarityValues = new double[6];
            
            // 初始化
            Arrays.fill(topSimilarityValues, -1);
            
            for (MedicinalPlants medicinalPlant : medicinalPlantsList) {
                double[] y = extractTimeVector(medicinalPlant);
                double similarity = computeCosineSimilarity(x, y);
                
                // 更新最相似的6个结果
                updateTopSimilar(topSimilar, topSimilarityValues, medicinalPlant.getName(), similarity);
            }
            
            // 设置相似度结果
            unknownPlant.setTop1(topSimilar[0]);
            unknownPlant.setTop2(topSimilar[1]);
            unknownPlant.setTop3(topSimilar[2]);
            unknownPlant.setTop4(topSimilar[3]);
            unknownPlant.setTop5(topSimilar[4]);
            unknownPlant.setTop6(topSimilar[5]);
            
            log.info("完成第{}条数据相似度计算，最高相似度：{}", i + 1, topSimilarityValues[0]);
        }
        
        log.info("余弦相似度计算完成");
    }
    
    /**
     * 提取时间向量数据
     */
    private double[] extractTimeVector(Object plant) {
        double[] vector = new double[202];
        
        try {
            Class<?> clazz = plant.getClass();
            for (int i = 1; i <= 202; i++) {
                String fieldName = "time" + i;
                java.lang.reflect.Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                Integer value = (Integer) field.get(plant);
                vector[i - 1] = value != null ? value.doubleValue() : 0.0;
            }
        } catch (Exception e) {
            log.error("提取时间向量数据失败", e);
            Arrays.fill(vector, 0.0);
        }
        
        return vector;
    }
    
    /**
     * 计算两个向量的余弦相似度
     * 公式：cos(θ) = (x·y) / (||x|| * ||y||)
     */
    private double computeCosineSimilarity(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("向量长度不匹配");
        }
        
        // 计算点积 x·y
        double dotProduct = 0.0;
        for (int i = 0; i < x.length; i++) {
            dotProduct += x[i] * y[i];
        }
        
        // 计算向量x的模长 ||x||
        double normX = 0.0;
        for (double xi : x) {
            normX += xi * xi;
        }
        normX = Math.sqrt(normX);
        
        // 计算向量y的模长 ||y||
        double normY = 0.0;
        for (double yi : y) {
            normY += yi * yi;
        }
        normY = Math.sqrt(normY);
        
        // 避免除零
        if (normX == 0.0 || normY == 0.0) {
            return 0.0;
        }
        
        // 计算余弦相似度
        return dotProduct / (normX * normY);
    }
    
    /**
     * 更新最相似的前6个结果
     */
    private void updateTopSimilar(String[] topNames, double[] topValues, String name, double similarity) {
        // 找到应该插入的位置
        int insertIndex = -1;
        for (int i = 0; i < topValues.length; i++) {
            if (similarity > topValues[i]) {
                insertIndex = i;
                break;
            }
        }
        
        if (insertIndex >= 0) {
            // 向后移动元素
            for (int i = topValues.length - 1; i > insertIndex; i--) {
                topValues[i] = topValues[i - 1];
                topNames[i] = topNames[i - 1];
            }
            // 插入新值
            topValues[insertIndex] = similarity;
            // similarity保留三位小数
            similarity = Math.round(similarity * 1000.0) / 1000.0;
            topNames[insertIndex] = name + "(" + similarity + ")";
        }
    }
}
