package com.project.phenolic.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author lhy
 * @since 2025-09-06
 */
@Getter
@Setter
public class Similarity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ExcelIgnore
    private Integer id;

    /**
     * 未知
     */
    @ExcelProperty("unknown sample")
    @ColumnWidth(25)
    private String unknownPlants;

    /**
     * 已知
     */
    @ExcelProperty("comparison sample")
    @ColumnWidth(27)
    private String medicinalPlants;

    /**
     * 相似度
     */
    @ExcelProperty("similarity")
    @ColumnWidth(20)
    private String similarity;

    /**
     * 批次
     */
    @ExcelIgnore
    private String batch;


}
