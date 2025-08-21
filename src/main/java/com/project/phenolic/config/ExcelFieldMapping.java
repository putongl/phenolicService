package com.project.phenolic.config;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Excel字段映射配置类
 * 用于定义Excel列名与实体字段的对应关系
 *
 * @author lhy
 * @since 2025-07-21
 */
@Data
@Component
public class ExcelFieldMapping {

    /**
     * Excel列名到实体字段名的映射
     * key: Excel中的列名
     * value: 实体类中的字段名
     */
    private Map<String, String> fieldMapping;

    /**
     * 字段类型映射
     * key: 字段名
     * value: 字段类型
     */
    private Map<String, Class<?>> fieldTypeMapping;

    public ExcelFieldMapping() {
        initDefaultMapping();
    }

    /**
     * 初始化默认映射关系
     */
    private void initDefaultMapping() {
        fieldMapping = new HashMap<>();
        fieldTypeMapping = new HashMap<>();

        // 基本字段映射
        fieldMapping.put("样本名称", "name");
        fieldMapping.put("name", "name");
        fieldMapping.put("名称", "name");
        
        fieldMapping.put("数值", "value");
        fieldMapping.put("值", "value");
        fieldMapping.put("测量值", "value");
        
        fieldMapping.put("批次", "batch");
        fieldMapping.put("批次号", "batch");
        fieldMapping.put("批号", "batch");
        
        fieldMapping.put("类别", "type");
        fieldMapping.put("分类", "type");
        fieldMapping.put("类型", "type");

        // 时间字段映射
        // 时间字段映射（四舍五入保留两位小数）
        fieldMapping.put("3.70", "time1");
        fieldMapping.put("3.89", "time2");
        fieldMapping.put("4.09", "time3");
        fieldMapping.put("4.26", "time4");
        fieldMapping.put("4.49", "time5");
        fieldMapping.put("4.71", "time6");
        fieldMapping.put("4.87", "time7");
        fieldMapping.put("5.10", "time8");
        fieldMapping.put("5.30", "time9");
        fieldMapping.put("5.51", "time10");
        fieldMapping.put("5.72", "time11");
        fieldMapping.put("5.86", "time12");
        fieldMapping.put("6.09", "time13");
        fieldMapping.put("6.30", "time14");
        fieldMapping.put("6.55", "time15");
        fieldMapping.put("6.69", "time16");
        fieldMapping.put("6.86", "time17");
        fieldMapping.put("7.14", "time18");
        fieldMapping.put("7.25", "time19");
        fieldMapping.put("7.47", "time20");
        fieldMapping.put("7.73", "time21");
        fieldMapping.put("7.86", "time22");
        fieldMapping.put("8.07", "time23");
        fieldMapping.put("8.16", "time24");
        fieldMapping.put("8.30", "time25");
        fieldMapping.put("8.48", "time26");
        fieldMapping.put("8.68", "time27");
        fieldMapping.put("8.93", "time28");
        fieldMapping.put("9.09", "time29");
        fieldMapping.put("9.29", "time30");
        fieldMapping.put("9.44", "time31");
        fieldMapping.put("9.54", "time32");
        fieldMapping.put("9.71", "time33");
        fieldMapping.put("9.88", "time34");
        fieldMapping.put("10.05", "time35");
        fieldMapping.put("10.17", "time36");
        fieldMapping.put("10.29", "time37");
        fieldMapping.put("10.46", "time38");
        fieldMapping.put("10.68", "time39");
        fieldMapping.put("10.91", "time40");
        fieldMapping.put("11.10", "time41");
        fieldMapping.put("11.22", "time42");
        fieldMapping.put("11.37", "time43");
        fieldMapping.put("11.52", "time44");
        fieldMapping.put("11.64", "time45");
        fieldMapping.put("11.88", "time46");
        fieldMapping.put("12.13", "time47");
        fieldMapping.put("12.28", "time48");
        fieldMapping.put("12.49", "time49");
        fieldMapping.put("12.69", "time50");
        fieldMapping.put("12.90", "time51");
        fieldMapping.put("13.09", "time52");
        fieldMapping.put("13.33", "time53");
        fieldMapping.put("13.44", "time54");
        fieldMapping.put("13.55", "time55");
        fieldMapping.put("13.69", "time56");
        fieldMapping.put("13.94", "time57");
        fieldMapping.put("14.06", "time58");
        fieldMapping.put("14.30", "time59");
        fieldMapping.put("14.48", "time60");
        fieldMapping.put("14.70", "time61");
        fieldMapping.put("14.84", "time62");
        fieldMapping.put("14.96", "time63");
        fieldMapping.put("15.10", "time64");
        fieldMapping.put("15.30", "time65");
        fieldMapping.put("15.51", "time66");
        fieldMapping.put("15.70", "time67");
        fieldMapping.put("15.90", "time68");
        fieldMapping.put("16.10", "time69");
        fieldMapping.put("16.26", "time70");
        fieldMapping.put("16.38", "time71");
        fieldMapping.put("16.51", "time72");
        fieldMapping.put("16.71", "time73");
        fieldMapping.put("16.88", "time74");
        fieldMapping.put("17.05", "time75");
        fieldMapping.put("17.13", "time76");
        fieldMapping.put("17.28", "time77");
        fieldMapping.put("17.45", "time78");
        fieldMapping.put("17.55", "time79");
        fieldMapping.put("17.69", "time80");
        fieldMapping.put("17.90", "time81");
        fieldMapping.put("18.10", "time82");
        fieldMapping.put("18.25", "time83");
        fieldMapping.put("18.36", "time84");
        fieldMapping.put("18.48", "time85");
        fieldMapping.put("18.71", "time86");
        fieldMapping.put("18.90", "time87");
        fieldMapping.put("19.13", "time88");
        fieldMapping.put("19.32", "time89");
        fieldMapping.put("19.49", "time90");
        fieldMapping.put("19.70", "time91");
        fieldMapping.put("19.89", "time92");
        fieldMapping.put("20.10", "time93");
        fieldMapping.put("20.23", "time94");
        fieldMapping.put("20.37", "time95");
        fieldMapping.put("20.46", "time96");
        fieldMapping.put("20.66", "time97");
        fieldMapping.put("20.75", "time98");
        fieldMapping.put("20.91", "time99");
        fieldMapping.put("21.09", "time100");
        fieldMapping.put("21.30", "time101");
        fieldMapping.put("21.46", "time102");
        fieldMapping.put("21.55", "time103");
        fieldMapping.put("21.71", "time104");
        fieldMapping.put("21.90", "time105");
        fieldMapping.put("22.06", "time106");
        fieldMapping.put("22.14", "time107");
        fieldMapping.put("22.29", "time108");
        fieldMapping.put("22.49", "time109");
        fieldMapping.put("22.72", "time110");
        fieldMapping.put("22.88", "time111");
        fieldMapping.put("23.05", "time112");
        fieldMapping.put("23.16", "time113");
        fieldMapping.put("23.29", "time114");
        fieldMapping.put("23.49", "time115");
        fieldMapping.put("23.70", "time116");
        fieldMapping.put("23.83", "time117");
        fieldMapping.put("23.95", "time118");
        fieldMapping.put("24.10", "time119");
        fieldMapping.put("24.28", "time120");
        fieldMapping.put("24.51", "time121");
        fieldMapping.put("24.67", "time122");
        fieldMapping.put("24.92", "time123");
        fieldMapping.put("25.11", "time124");
        fieldMapping.put("25.26", "time125");
        fieldMapping.put("25.52", "time126");
        fieldMapping.put("25.72", "time127");
        fieldMapping.put("25.88", "time128");
        fieldMapping.put("26.06", "time129");
        fieldMapping.put("26.14", "time130");
        fieldMapping.put("26.32", "time131");
        fieldMapping.put("26.52", "time132");
        fieldMapping.put("26.70", "time133");
        fieldMapping.put("26.89", "time134");
        fieldMapping.put("27.10", "time135");
        fieldMapping.put("27.29", "time136");
        fieldMapping.put("27.50", "time137");
        fieldMapping.put("27.70", "time138");
        fieldMapping.put("27.90", "time139");
        fieldMapping.put("28.08", "time140");
        fieldMapping.put("28.32", "time141");
        fieldMapping.put("28.50", "time142");
        fieldMapping.put("28.69", "time143");
        fieldMapping.put("28.89", "time144");
        fieldMapping.put("29.04", "time145");
        fieldMapping.put("29.14", "time146");
        fieldMapping.put("29.31", "time147");
        fieldMapping.put("29.46", "time148");
        fieldMapping.put("29.70", "time149");
        fieldMapping.put("29.89", "time150");
        fieldMapping.put("30.13", "time151");
        fieldMapping.put("30.29", "time152");
        fieldMapping.put("30.45", "time153");
        fieldMapping.put("30.54", "time154");
        fieldMapping.put("30.69", "time155");
        fieldMapping.put("30.90", "time156");
        fieldMapping.put("31.10", "time157");
        fieldMapping.put("31.34", "time158");
        fieldMapping.put("31.47", "time159");
        fieldMapping.put("31.71", "time160");
        fieldMapping.put("31.92", "time161");
        fieldMapping.put("32.09", "time162");
        fieldMapping.put("32.28", "time163");
        fieldMapping.put("32.48", "time164");
        fieldMapping.put("32.68", "time165");
        fieldMapping.put("32.89", "time166");
        fieldMapping.put("33.12", "time167");
        fieldMapping.put("33.30", "time168");
        fieldMapping.put("33.52", "time169");
        fieldMapping.put("33.68", "time170");
        fieldMapping.put("33.89", "time171");
        fieldMapping.put("34.08", "time172");
        fieldMapping.put("34.28", "time173");
        fieldMapping.put("34.53", "time174");
        fieldMapping.put("34.67", "time175");
        fieldMapping.put("34.92", "time176");
        fieldMapping.put("35.07", "time177");
        fieldMapping.put("35.27", "time178");
        fieldMapping.put("35.49", "time179");
        fieldMapping.put("35.56", "time180");
        fieldMapping.put("35.70", "time181");
        fieldMapping.put("35.83", "time182");
        fieldMapping.put("35.95", "time183");
        fieldMapping.put("36.14", "time184");
        fieldMapping.put("36.31", "time185");
        fieldMapping.put("36.51", "time186");
        fieldMapping.put("36.68", "time187");
        fieldMapping.put("36.90", "time188");
        fieldMapping.put("37.08", "time189");
        fieldMapping.put("37.30", "time190");
        fieldMapping.put("37.50", "time191");
        fieldMapping.put("37.67", "time192");
        fieldMapping.put("37.87", "time193");
        fieldMapping.put("38.11", "time194");
        fieldMapping.put("38.33", "time195");
        fieldMapping.put("38.50", "time196");
        fieldMapping.put("38.70", "time197");
        fieldMapping.put("38.93", "time198");
        fieldMapping.put("39.09", "time199");
        fieldMapping.put("39.31", "time200");
        fieldMapping.put("39.51", "time201");
        fieldMapping.put("39.69", "time202");


        // 字段类型映射
        fieldTypeMapping.put("name", String.class);
        fieldTypeMapping.put("value", Double.class);
        fieldTypeMapping.put("batch", Integer.class);
        fieldTypeMapping.put("type", String.class);
        
        // 时间字段都是Integer类型
        for (int i = 1; i <= 202; i++) {
            fieldTypeMapping.put("time" + i, Integer.class);
        }
    }

    /**
     * 根据Excel列名获取实体字段名
     *
     * @param excelColumnName Excel列名
     * @return 实体字段名，如果没有映射则返回null
     */
    public String getFieldName(String excelColumnName) {
        if (excelColumnName == null) {
            return null;
        }
        
        // 先尝试精确匹配
        String fieldName = fieldMapping.get(excelColumnName.trim());
        if (fieldName != null) {
            return fieldName;
        }
        
        // 尝试忽略大小写匹配
        for (Map.Entry<String, String> entry : fieldMapping.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(excelColumnName.trim())) {
                return entry.getValue();
            }
        }
        
        return null;
    }

    /**
     * 根据字段名获取字段类型
     *
     * @param fieldName 字段名
     * @return 字段类型
     */
    public Class<?> getFieldType(String fieldName) {
        return fieldTypeMapping.get(fieldName);
    }

    /**
     * 添加自定义映射
     *
     * @param excelColumnName Excel列名
     * @param fieldName 实体字段名
     * @param fieldType 字段类型
     */
    public void addMapping(String excelColumnName, String fieldName, Class<?> fieldType) {
        fieldMapping.put(excelColumnName, fieldName);
        fieldTypeMapping.put(fieldName, fieldType);
    }

    /**
     * 批量添加映射
     *
     * @param mappings 映射关系Map
     */
    public void addMappings(Map<String, String> mappings) {
        fieldMapping.putAll(mappings);
    }

    /**
     * 获取所有支持的Excel列名
     *
     * @return Excel列名集合
     */
    public java.util.Set<String> getSupportedExcelColumns() {
        return fieldMapping.keySet();
    }

    /**
     * 检查Excel列名是否被支持
     *
     * @param excelColumnName Excel列名
     * @return 是否支持
     */
    public boolean isSupported(String excelColumnName) {
        return getFieldName(excelColumnName) != null;
    }
}
