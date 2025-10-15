package com.project.phenolic.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author lhy
 * @since 2025-10-14
 */
@Getter
@Setter
@TableName("unknown_content")
public class UnknownContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty("name")
    @ColumnWidth(15)
    private String name;

    @ExcelProperty("GAL")
    @ColumnWidth(15)
    private Double gal;

    @ExcelProperty("1-CQA")
    @ColumnWidth(15)
    private Double cqa1;

    @ExcelProperty("5-CQA")
    @ColumnWidth(15)
    private Double cqa5;

    @ExcelProperty("3-CQA")
    @ColumnWidth(15)
    private Double cqa3;

    @ExcelProperty("4-CQA")
    @ColumnWidth(15)
    private Double cqa4;

    @ExcelProperty("CAF")
    @ColumnWidth(15)
    private Double caf;

    @ExcelProperty("SYR")
    @ColumnWidth(15)
    private Double syr;

    @ExcelProperty("1,3-DQA")
    @ColumnWidth(15)
    private Double dqa13;

    @ExcelProperty("p-COU")
    @ColumnWidth(15)
    private Double pcou;

    @ExcelProperty("RUT")
    @ColumnWidth(15)
    private Double rut;

    @ExcelProperty("HYP")
    @ColumnWidth(15)
    private Double hyp;

    @ExcelProperty("ISO")
    @ColumnWidth(15)
    private Double iso;

    @ExcelProperty("LUT")
    @ColumnWidth(15)
    private Double lut;

    @ExcelProperty("3,4-DQA")
    @ColumnWidth(15)
    private Double dqa34;

    @ExcelProperty("QUE")
    @ColumnWidth(15)
    private Double que;

    @ExcelProperty("3,5-DQA")
    @ColumnWidth(15)
    private Double dqa35;

    @ExcelProperty("4,5-DQA")
    @ColumnWidth(15)
    private Double dqa45;

    @ExcelIgnore
    private String batch;


}
