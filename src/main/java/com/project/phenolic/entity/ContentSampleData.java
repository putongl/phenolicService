package com.project.phenolic.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

@Data
public class ContentSampleData {

    @ExcelProperty("化合物")
    @ColumnWidth(20)
    private String compound;

    @ExcelProperty("缩写")
    @ColumnWidth(15)
    private String shortName;

    @ExcelProperty("称样量（g）")
    @ColumnWidth(15)
    private Double mass;

    @ExcelProperty("体积（ml）")
    @ColumnWidth(15)
    private Double volume;

    @ExcelProperty("峰面积")
    @ColumnWidth(15)
    private Double area;
}
