package com.project.phenolic.controller;

import com.project.phenolic.common.Result;
import com.project.phenolic.entity.dto.DbDisplayDTO;
import com.project.phenolic.service.IDbDisplayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lhy
 * @since 2025-09-17
 */
@Slf4j
@RestController
@RequestMapping("/dbDisplay")
public class DbDisplayController {

    @Autowired
    private IDbDisplayService dbDisplayService;

    /**
     * 根据植物名称查询数据
     * @param name 植物名称
     * @return 包含plantName、timePoint和value的列表
     */
    @GetMapping("/getData")
    public Result getData(@RequestParam String name) {
        List<DbDisplayDTO> data = dbDisplayService.getDisplayDataByPlantName(name);
        return Result.success(data);
    }
}
