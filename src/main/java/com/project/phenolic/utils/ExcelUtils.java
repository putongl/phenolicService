package com.project.phenolic.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.util.ListUtils;
import com.project.phenolic.config.ExcelFieldMapping;
import com.project.phenolic.entity.dto.MedicinalPlantsEnhancedImportDTO;
import com.project.phenolic.listener.MedicinalPlantsExcelListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.function.Consumer;

/**
 * Excel工具类
 *
 * @author lhy
 * @since 2025-07-15
 */
@Slf4j
public class ExcelUtils {

    /**
     * 下载Excel模板
     *
     * @param response  响应对象
     * @param fileName  文件名
     * @param clazz     数据类型
     * @param data      示例数据
     */
    public static <T> void downloadTemplate(HttpServletResponse response, String fileName, Class<T> clazz, List<T> data) {
        log.info("开始下载Excel模板，文件名：{}", fileName);

        try {
            // 设置响应头
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");

            // 防止中文乱码，使用更安全的编码方式
            String encodedFileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + encodedFileName + ".xlsx");
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");

            log.info("响应头设置完成，开始写入Excel数据，数据条数：{}", data != null ? data.size() : 0);

            // 写入Excel数据
            EasyExcel.write(response.getOutputStream(), clazz)
                    .sheet("模板")
                    .doWrite(data);

            // 刷新输出流
            response.getOutputStream().flush();

            log.info("Excel模板下载完成：{}", fileName);

        } catch (IOException e) {
            log.error("下载Excel模板失败，文件名：{}，错误信息：{}", fileName, e.getMessage(), e);

            // 尝试设置错误响应
            try {
                if (!response.isCommitted()) {
                    response.reset();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().write("{\"success\":false,\"message\":\"下载Excel模板失败：" + e.getMessage() + "\"}");
                }
            } catch (IOException ioException) {
                log.error("设置错误响应失败", ioException);
            }

            throw new RuntimeException("下载Excel模板失败", e);
        } catch (Exception e) {
            log.error("下载Excel模板时发生未知错误，文件名：{}，错误信息：{}", fileName, e.getMessage(), e);
            throw new RuntimeException("下载Excel模板失败", e);
        }
    }

    /**
     * 导出Excel数据
     *
     * @param response  响应对象
     * @param fileName  文件名
     * @param clazz     数据类型
     * @param data      数据列表
     * @param sheetName 工作表名称
     */
    public static <T> void exportExcel(HttpServletResponse response, String fileName, Class<T> clazz, List<T> data, String sheetName) {
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 防止中文乱码
            fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            EasyExcel.write(response.getOutputStream(), clazz)
                    .sheet(sheetName)
                    .doWrite(data);
        } catch (IOException e) {
            log.error("导出Excel数据失败", e);
            throw new RuntimeException("导出Excel数据失败", e);
        }
    }

    /**
     * 读取Excel文件
     *
     * @param file      上传的文件
     * @param clazz     数据类型
     * @param consumer  数据处理函数
     */
    public static <T> void readExcel(MultipartFile file, Class<T> clazz, Consumer<List<T>> consumer) {
        try {
            EasyExcel.read(file.getInputStream(), clazz, new PageReadListener<T>(consumer))
                    .sheet()
                    .doRead();
        } catch (IOException e) {
            log.error("读取Excel文件失败", e);
            throw new RuntimeException("读取Excel文件失败", e);
        }
    }

    /**
     * 简单的读取Excel文件并返回所有数据
     *
     * @param file  上传的文件
     * @param clazz 数据类型
     * @return 数据列表
     */
    public static <T> List<T> readExcelToList(MultipartFile file, Class<T> clazz) {
        List<T> list = ListUtils.newArrayList();
        try {
            EasyExcel.read(file.getInputStream(), clazz, new PageReadListener<T>(list::addAll))
                    .sheet()
                    .doRead();
        } catch (IOException e) {
            log.error("读取Excel文件失败", e);
            throw new RuntimeException("读取Excel文件失败", e);
        }
        return list;
    }

    /**
     * 使用自定义字段映射读取Excel文件
     *
     * @param file Excel文件
     * @param fieldMapping 字段映射配置
     * @return 导入结果
     */
    public static Map<String, Object> readExcelWithMapping(MultipartFile file, ExcelFieldMapping fieldMapping) {
        log.info("开始使用自定义映射读取Excel文件：{}", file.getOriginalFilename());

        Map<String, Object> result = new HashMap<>();
        List<MedicinalPlantsEnhancedImportDTO> successList = new ArrayList<>();
        List<String> errorMessages = new ArrayList<>();

        try {
            // 创建自定义监听器
            MedicinalPlantsExcelListener listener = new MedicinalPlantsExcelListener(
                fieldMapping,
                dataList -> {
                    successList.addAll(dataList);
                    log.debug("批量处理 {} 条数据", dataList.size());
                }
            );

            // 读取Excel文件
            EasyExcel.read(file.getInputStream(), listener)
                    .sheet()
                    .headRowNumber(1) // 第一行是表头
                    .doRead();

            // 收集错误信息
            errorMessages.addAll(listener.getErrorMessages());

            // 构建返回结果
            result.put("success", true);
            result.put("message", "Excel读取完成");
            result.put("totalCount", successList.size() + errorMessages.size());
            result.put("successCount", successList.size());
            result.put("errorCount", errorMessages.size());
            result.put("successData", successList);
            result.put("errorMessages", errorMessages);

            log.info("Excel文件读取完成，成功：{} 条，失败：{} 条", successList.size(), errorMessages.size());

        } catch (IOException e) {
            log.error("读取Excel文件失败：{}", e.getMessage(), e);
            result.put("success", false);
            result.put("message", "读取Excel文件失败：" + e.getMessage());
            result.put("totalCount", 0);
            result.put("successCount", 0);
            result.put("errorCount", 0);
            result.put("successData", new ArrayList<>());
            result.put("errorMessages", Arrays.asList("文件读取失败：" + e.getMessage()));

        } catch (Exception e) {
            log.error("处理Excel文件时发生未知错误：{}", e.getMessage(), e);
            result.put("success", false);
            result.put("message", "处理Excel文件失败：" + e.getMessage());
            result.put("totalCount", 0);
            result.put("successCount", 0);
            result.put("errorCount", 0);
            result.put("successData", new ArrayList<>());
            result.put("errorMessages", Arrays.asList("处理失败：" + e.getMessage()));
        }

        return result;
    }

    /**
     * 验证Excel文件格式
     *
     * @param file 上传的文件
     * @return 验证结果
     */
    public static String validateExcelFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return "请选择要上传的文件";
        }

        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            return "文件名不能为空";
        }

        if (!fileName.toLowerCase().endsWith(".xlsx") && !fileName.toLowerCase().endsWith(".xls")) {
            return "请上传Excel格式的文件（.xlsx 或 .xls）";
        }

        // 检查文件大小（限制为10MB）
        long maxSize = 10 * 1024 * 1024; // 10MB
        if (file.getSize() > maxSize) {
            return "文件大小不能超过10MB";
        }

        return null; // 验证通过
    }
}
