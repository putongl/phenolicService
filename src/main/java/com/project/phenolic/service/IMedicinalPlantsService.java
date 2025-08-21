package com.project.phenolic.service;

import com.project.phenolic.entity.MedicinalPlants;
import com.project.phenolic.entity.dto.MedicinalPlantsEnhancedImportDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhy
 * @since 2025-07-21
 */
public interface IMedicinalPlantsService extends IService<MedicinalPlants> {

    /**
     * 使用自定义字段映射导入Excel数据
     *
     * @param file Excel文件
     * @return 导入结果
     */
    Map<String, Object> importExcelWithMapping(MultipartFile file);

    /**
     * 批量保存增强版导入数据
     *
     * @param importDataList 导入数据列表
     * @return 保存结果
     */
    Map<String, Object> batchSaveImportData(List<MedicinalPlantsEnhancedImportDTO> importDataList);

    /**
     * 获取支持的Excel列名列表
     *
     * @return 支持的列名列表
     */
    List<String> getSupportedExcelColumns();
}
