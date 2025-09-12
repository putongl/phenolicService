package com.project.phenolic.entity;

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
@TableName("variety_detail")
public class VarietyDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sampleName;

    private String manufacturer;

    private String placeOrigin;

    private String batch;

    private String collectionDate;

    private String internalId;


}
