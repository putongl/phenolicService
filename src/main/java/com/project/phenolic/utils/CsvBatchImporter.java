package com.project.phenolic.utils;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CsvBatchImporter {

    // 数据库连接信息
    private static final String URL = "jdbc:mysql://localhost:3306/phenolic_acidsdb?useSSL=false&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) throws Exception {
        // 目标文件夹路径
        String folderPath = "C:\\Users\\liu\\Desktop\\CSV文件 - 0-40min";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            File folder = new File(folderPath);
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));

            if (files == null || files.length == 0) {
                System.out.println("目录下没有找到 CSV 文件！");
                return;
            }

            for (File file : files) {
                importCsv(conn, file);
            }
        }
    }

    public static void importCsv(Connection conn, File file) throws Exception {
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            reader.readNext();
            String[] firstRow =  reader.readNext();
            if (firstRow == null) return;

            String sampleName = firstRow[0].replace("\"", "");

            String[] row;
            String sql = "INSERT INTO db_display (plant_name, chinese_name, time_point, value) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            String fileNameWithoutExt = file.getName().replaceFirst("[.][^.]+$", "");

            while ((row = reader.readNext()) != null) {
                if (row.length < 2) continue;
                try {
                    double timePoint = Double.parseDouble(row[0]);
                    double value = Double.parseDouble(row[1]);

                    ps.setString(1, fileNameWithoutExt);
                    ps.setString(2, sampleName);
                    ps.setDouble(3, timePoint);
                    ps.setDouble(4, value);
                    ps.addBatch();
                } catch (NumberFormatException e) {
                    System.out.println("跳过无效数据行: " + String.join(",", row));
                }
            }

            ps.executeBatch();
            ps.close();

            System.out.println(file.getName() + " 导入完成！");
        }
    }
}