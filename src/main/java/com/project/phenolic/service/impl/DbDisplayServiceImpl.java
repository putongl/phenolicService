package com.project.phenolic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.phenolic.entity.dto.DbDisplayDTO;
import com.project.phenolic.entity.DbDisplay;
import com.project.phenolic.mapper.DbDisplayMapper;
import com.project.phenolic.service.IDbDisplayService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhy
 * @since 2025-09-17
 */
@Service
public class DbDisplayServiceImpl extends ServiceImpl<DbDisplayMapper, DbDisplay> implements IDbDisplayService {

    @Override
    public List<DbDisplayDTO> getDisplayDataByPlantName(String plantName) {
        LambdaQueryWrapper<DbDisplay> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DbDisplay::getPlantName, plantName)
                   .select(DbDisplay::getPlantName, DbDisplay::getTimePoint, DbDisplay::getValue);
        
        return list(queryWrapper).stream()
                .map(entity -> {
                    DbDisplayDTO dto = new DbDisplayDTO();
                    dto.setPlantName(entity.getPlantName());
                    dto.setTimePoint(entity.getTimePoint());
                    dto.setValue(entity.getValue());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
