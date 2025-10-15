package com.project.phenolic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author lhy
 * @since 2025-10-11
 */
@Data
@TableName("content_info")
public class ContentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Double gal;

    private Double cqa1;

    private Double cqa5;

    private Double cqa3;

    private Double cqa4;

    private Double caf;

    private Double syr;

    private Double dqa13;

    private Double pcou;

    private Double rut;

    private Double hyp;

    private Double iso;

    private Double lut;

    private Double dqa34;

    private Double que;

    private Double dqa35;

    private Double dqa45;


}
