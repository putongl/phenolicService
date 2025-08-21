package com.project.phenolic.service.impl;

import com.project.phenolic.config.ExcelFieldMapping;
import com.project.phenolic.entity.MedicinalPlants;
import com.project.phenolic.entity.dto.MedicinalPlantsEnhancedImportDTO;
import com.project.phenolic.mapper.MedicinalPlantsMapper;
import com.project.phenolic.service.IMedicinalPlantsService;
import com.project.phenolic.utils.ExcelUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
 * @since 2025-07-21
 */
@Slf4j
@Service
public class MedicinalPlantsServiceImpl extends ServiceImpl<MedicinalPlantsMapper, MedicinalPlants> implements IMedicinalPlantsService {

    @Autowired
    private ExcelFieldMapping excelFieldMapping;

    @Override
    public Map<String, Object> importExcelWithMapping(MultipartFile file) {
        log.info("开始使用自定义映射导入Excel文件：{}", file.getOriginalFilename());

        // 验证文件
        String validationError = ExcelUtils.validateExcelFile(file);
        if (validationError != null) {
            Map<String, Object> result = new java.util.HashMap<>();
            result.put("success", false);
            result.put("message", validationError);
            return result;
        }

        try {
            // 使用自定义映射读取Excel
            Map<String, Object> readResult = ExcelUtils.readExcelWithMapping(file, excelFieldMapping);

            if (!(Boolean) readResult.get("success")) {
                return readResult;
            }

            // 获取成功读取的数据
            @SuppressWarnings("unchecked")
            List<MedicinalPlantsEnhancedImportDTO> importDataList =
                (List<MedicinalPlantsEnhancedImportDTO>) readResult.get("successData");

            if (importDataList.isEmpty()) {
                Map<String, Object> result = new HashMap<>();
                result.put("success", false);
                result.put("message", "Excel文件中没有有效数据");
                return result;
            }
            importDataList.forEach(dto -> {
                dto.setType(dto.getName().split("-")[0]);
            });

            // 批量保存数据
            Map<String, Object> saveResult = batchSaveImportData(importDataList);

            // 合并结果
            Map<String, Object> finalResult = new java.util.HashMap<>();
            finalResult.put("success", (Boolean) saveResult.get("success"));
            finalResult.put("message", saveResult.get("message"));
            finalResult.put("totalCount", readResult.get("totalCount"));
            finalResult.put("successCount", saveResult.get("successCount"));
            finalResult.put("errorCount", readResult.get("errorCount"));
            finalResult.put("readErrorMessages", readResult.get("errorMessages"));
            finalResult.put("saveErrorMessages", saveResult.get("errorMessages"));

            return finalResult;

        } catch (Exception e) {
            log.error("导入Excel文件失败", e);
            Map<String, Object> result = new java.util.HashMap<>();
            result.put("success", false);
            result.put("message", "导入失败：" + e.getMessage());
            return result;
        }
    }

    @Override
    public Map<String, Object> batchSaveImportData(List<MedicinalPlantsEnhancedImportDTO> importDataList) {
        log.info("开始批量保存导入数据，共 {} 条", importDataList.size());

        Map<String, Object> result = new java.util.HashMap<>();
        List<String> errorMessages = new ArrayList<>();
        int successCount = 0;

        try {
            List<MedicinalPlants> entityList = new ArrayList<>();

            for (int i = 0; i < importDataList.size(); i++) {
                MedicinalPlantsEnhancedImportDTO dto = importDataList.get(i);

                try {
                    // 转换为实体对象
                    MedicinalPlants entity = convertToEntity(dto);
                    entityList.add(entity);
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
     * 将DTO转换为实体对象
     */
    private MedicinalPlants convertToEntity(MedicinalPlantsEnhancedImportDTO dto) {
        MedicinalPlants entity = new MedicinalPlants();

        // 复制基本属性
        BeanUtils.copyProperties(dto, entity);

        return entity;
    }
}
