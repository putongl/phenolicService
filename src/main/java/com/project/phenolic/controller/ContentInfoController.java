package com.project.phenolic.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.phenolic.common.Result;
import com.project.phenolic.entity.ContentInfo;
import com.project.phenolic.entity.ContentSampleData;
import com.project.phenolic.service.IContentInfoService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lhy
 * @since 2025-10-11
 */
@RestController
@RequestMapping("/contentInfo")
public class ContentInfoController {

    @Autowired
    private IContentInfoService contentInfoService;


    @PostMapping("/updateType")
    public Result updateType() {
        List<ContentInfo> list = contentInfoService.list();
        for (ContentInfo contentInfo : list) {
            String name = contentInfo.getName();
            String type = name.split("-")[0];

            // 1. 构建更新条件：以name为唯一标识
            ContentInfo query = new ContentInfo();
            query.setName(name); // 用name作为查询条件

            // 2. 构建更新内容：只设置需要更新的type字段
            ContentInfo update = new ContentInfo();
            update.setType(type);

            contentInfoService.update(update, new QueryWrapper<>(query));

        }

        return Result.success("更新成功");
    }

    @PostMapping("/queryRange")
    public Result queryRange(@RequestParam("type") String type, @RequestParam("phenolic") String phenolic) {
        List<ContentInfo> list = contentInfoService.lambdaQuery().eq(ContentInfo::getType, type).list();

        ArrayList<Double> contentList = new ArrayList<>();

        for (ContentInfo contentInfo : list) {
            switch (phenolic.toUpperCase()) {
                case "GAL":
                    contentList.add(contentInfo.getGal());
                    break;
                case "1-CQA":
                    contentList.add(contentInfo.getCqa1());
                    break;
                case "5-CQA":
                    contentList.add(contentInfo.getCqa5());
                    break;
                case "3-CQA":
                    contentList.add(contentInfo.getCqa3());
                    break;
                case "4-CQA":
                    contentList.add(contentInfo.getCqa4());
                    break;
                case "CAF":
                    contentList.add(contentInfo.getCaf());
                    break;
                case "SYR":
                    contentList.add(contentInfo.getSyr());
                    break;
                case "1,3-DQA":
                    contentList.add(contentInfo.getDqa13());
                    break;
                case "P-COU":
                    contentList.add(contentInfo.getPcou());
                    break;
                case "RUT":
                    contentList.add(contentInfo.getRut());
                    break;
                case "HYP":
                    contentList.add(contentInfo.getHyp());
                    break;
                case "ISO":
                    contentList.add(contentInfo.getIso());
                    break;
                case "LUT":
                    contentList.add(contentInfo.getLut());
                    break;
                case "3,4-DQA":
                    contentList.add(contentInfo.getDqa34());
                    break;
                case "QUE":
                    contentList.add(contentInfo.getQue());
                    break;
                case "3,5-DQA":
                    contentList.add(contentInfo.getDqa35());
                    break;
                case "4,5-DQA":
                    contentList.add(contentInfo.getDqa45());
                    break;
            }

        }

        // 获取最大值和最小值
        double min = Collections.min(contentList);
        double max = Collections.max(contentList);

        // 保留两位小数并格式化
        DecimalFormat df = new DecimalFormat("#0.00");
        String result = df.format(min) + "-" + df.format(max);

        return Result.success(result);
    }




}
