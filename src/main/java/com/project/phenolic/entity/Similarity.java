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
    @ExcelProperty("未知样本")
    @ColumnWidth(15)
    private String unknownPlants;

    /**
     * 已知
     */
    @ExcelProperty("对比样本")
    @ColumnWidth(15)
    private String medicinalPlants;

    /**
     * 相似度
     */
    @ExcelProperty("相似度")
    @ColumnWidth(20)
    private String similarity;

    /**
     * 批次
     */
    @ExcelIgnore
    private String batch;


}
