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
 * @since 2025-09-17
 */
@Getter
@Setter
@TableName("db_display")
public class DbDisplay implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 英文名称
     */
    private String plantName;

    /**
     * 中文名称
     */
    private String chineseName;

    /**
     * 时间点
     */
    private Double timePoint;

    /**
     * 值
     */
    private Double value;


}
