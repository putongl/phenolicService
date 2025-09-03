package com.project.phenolic.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.project.phenolic.config.ExcelFieldMapping;
import com.project.phenolic.entity.UnknownPlants;
import com.project.phenolic.entity.UnknownPlants;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 药用植物Excel读取监听器
 * 支持动态字段映射
 *
 * @author lhy
 * @since 2025-07-21
 */
@Slf4j
public class UnknownMedicinalPlantsExcelListener extends AnalysisEventListener<Map<Integer, String>> {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list，方便内存回收
     */
    private static final int BATCH_COUNT = 1000;

    /**
     * 缓存的数据
     */
    private List<UnknownPlants> cachedDataList = new ArrayList<>();

    /**
     * 字段映射配置
     */
    private ExcelFieldMapping fieldMapping;

    /**
     * 表头映射 - 列索引到字段名的映射
     */
    private Map<Integer, String> headerMap;

    /**
     * 数据处理回调
     */
    private DataProcessor dataProcessor;

    /**
     * 错误信息收集
     */
    private List<String> errorMessages = new ArrayList<>();

    public UnknownMedicinalPlantsExcelListener(ExcelFieldMapping fieldMapping, DataProcessor dataProcessor) {
        this.fieldMapping = fieldMapping;
        this.dataProcessor = dataProcessor;
    }

    /**
     * 读取表头信息
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("解析到表头数据：{}", headMap);
        this.headerMap = new java.util.HashMap<>();
        
        // 将Excel列名映射为实体字段名
        for (Map.Entry<Integer, String> entry : headMap.entrySet()) {
            String excelColumnName = entry.getValue();
            String fieldName = fieldMapping.getFieldName(excelColumnName);
            
            if (fieldName != null) {
                this.headerMap.put(entry.getKey(), fieldName);
                log.debug("映射Excel列 '{}' -> 字段 '{}'", excelColumnName, fieldName);
            } else {
                log.warn("未找到Excel列 '{}' 的字段映射", excelColumnName);
            }
        }
        
        log.info("表头映射完成，共映射 {} 个字段", this.headerMap.size());
    }

    /**
     * 读取数据
     */
    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        log.debug("解析到一条数据：{}", data);
        
        try {
            UnknownPlants dto = convertToDTO(data, context.readRowHolder().getRowIndex());
            if (dto != null) {
                cachedDataList.add(dto);
            }
            
            // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
            if (cachedDataList.size() >= BATCH_COUNT) {
                saveData();
                // 存储完成清理 list
                cachedDataList.clear();
            }
        } catch (Exception e) {
            String errorMsg = String.format("第%d行数据解析失败：%s", 
                context.readRowHolder().getRowIndex() + 1, e.getMessage());
            log.error(errorMsg, e);
            errorMessages.add(errorMsg);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！共处理 {} 条数据", cachedDataList.size());
    }

    /**
     * 将Map数据转换为DTO对象
     */
    private UnknownPlants convertToDTO(Map<Integer, String> data, int rowIndex) {
        UnknownPlants dto = new UnknownPlants();
        
        try {
            for (Map.Entry<Integer, String> entry : data.entrySet()) {
                Integer columnIndex = entry.getKey();
                String cellValue = entry.getValue();
                
                // 获取字段名
                String fieldName = headerMap.get(columnIndex);
                if (fieldName == null || cellValue == null || cellValue.trim().isEmpty()) {
                    continue;
                }
                
                // 设置字段值
                setFieldValue(dto, fieldName, cellValue.trim(), rowIndex);
            }

            
        } catch (Exception e) {
            String errorMsg = String.format("第%d行数据转换失败：%s", rowIndex + 1, e.getMessage());
            log.error(errorMsg, e);
            errorMessages.add(errorMsg);
            return null;
        }
        
        return dto;
    }

    /**
     * 设置字段值
     */
    private void setFieldValue(UnknownPlants dto, String fieldName, String value, int rowIndex) {
        try {
            Field field = UnknownPlants.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            
            Class<?> fieldType = fieldMapping.getFieldType(fieldName);
            Object convertedValue = convertValue(value, fieldType, fieldName, rowIndex);
            
            if (convertedValue != null) {
                field.set(dto, convertedValue);
            }
            
        } catch (NoSuchFieldException e) {
            log.warn("字段 '{}' 在DTO中不存在", fieldName);
        } catch (Exception e) {
            String errorMsg = String.format("第%d行设置字段 '%s' 值失败：%s", rowIndex + 1, fieldName, e.getMessage());
            log.error(errorMsg, e);
            errorMessages.add(errorMsg);
        }
    }

    /**
     * 值类型转换
     */
    private Object convertValue(String value, Class<?> targetType, String fieldName, int rowIndex) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        
        try {
            if (targetType == String.class) {
                return value;
            } else if (targetType == Integer.class) {
                // 处理可能的小数点
                if (value.contains(".")) {
                    return (int) Double.parseDouble(value);
                }
                return Integer.parseInt(value);
            } else if (targetType == Double.class) {
                return Double.parseDouble(value);
            } else if (targetType == Long.class) {
                return Long.parseLong(value);
            }
        } catch (NumberFormatException e) {
            String errorMsg = String.format("第%d行字段 '%s' 值 '%s' 无法转换为 %s 类型", 
                rowIndex + 1, fieldName, value, targetType.getSimpleName());
            log.warn(errorMsg);
            errorMessages.add(errorMsg);
        }
        
        return null;
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        if (cachedDataList.isEmpty()) {
            return;
        }
        
        log.info("开始保存 {} 条数据", cachedDataList.size());
        if (dataProcessor != null) {
            dataProcessor.process(new ArrayList<>(cachedDataList));
        }
    }

    /**
     * 获取错误信息
     */
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    /**
     * 数据处理接口
     */
    public interface DataProcessor {
        void process(List<UnknownPlants> dataList);
    }
}
