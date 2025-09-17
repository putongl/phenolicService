package com.project.phenolic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.phenolic.entity.dto.DbDisplayDTO;
import com.project.phenolic.entity.DbDisplay;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhy
 * @since 2025-09-17
 */
public interface IDbDisplayService extends IService<DbDisplay> {
    
    /**
     * 根据植物名称查询数据，只返回指定字段
     * @param plantName 植物名称
     * @return 包含plantName、timePoint和value的DTO列表
     */
    List<DbDisplayDTO> getDisplayDataByPlantName(String plantName);
}
