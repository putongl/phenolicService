package com.project.phenolic.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

@Data
public class ContentSampleData {

    @ExcelProperty("compound")
    @ColumnWidth(20)
    private String compound;

    @ExcelProperty("abbreviation")
    @ColumnWidth(20)
    private String shortName;

    @ExcelProperty("weighing sample（g）")
    @ColumnWidth(33)
    private Double mass;

    @ExcelProperty("volume（ml）")
    @ColumnWidth(20)
    private Double volume;

    @ExcelProperty("peak area")
    @ColumnWidth(20)
    private Double area;
}
