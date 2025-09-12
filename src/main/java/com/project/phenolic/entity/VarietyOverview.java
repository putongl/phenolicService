package com.project.phenolic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2025-09-10
 */
@Getter
@Setter
@TableName("variety_overview")
public class VarietyOverview implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String productName;

    private String chineseName;

    private String abbreviation;

    private String sourceMaterial;

    private String originalPlant;

    private String family;

    private String processingTechnology;

    private String category;

    private String batches;


}
