package com.project.phenolic.service;

import com.project.phenolic.common.Result;
import com.project.phenolic.entity.UnknownPlants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.phenolic.entity.dto.MedicinalPlantsEnhancedImportDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhy
 * @since 2025-08-22
 */
public interface IUnknownPlantsService extends IService<UnknownPlants> {

    Result batchSaveImportData(MultipartFile file, Map<String, String> requestParams);

    /**
     * 批量保存增强版导入数据
     *
     * @param importDataList 导入数据列表
     * @return 保存结果
     */
    Map<String, Object> batchSave(List<UnknownPlants> importDataList);

    /**
     * 获取支持的Excel列名列表
     *
     * @return 支持的列名列表
     */
    List<String> getSupportedExcelColumns();
}
