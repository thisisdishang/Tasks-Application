/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finlogic.task.util;

import com.finlogic.task.model.Tasks;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportExcel {

    public byte[] export(List<Tasks> exportTask) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Tasks");
            // Create Header Row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Task ID", "Task Description", "Status", "Task Allocate Date", "Task End Date", "User ID"};
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }   // Populate Data
            int rowNum = 1;
            for (Tasks t : exportTask) {
                Row row = sheet.createRow(rowNum);
                row.createCell(0).setCellValue(t.getTASK_ID());
                row.createCell(1).setCellValue(t.getTASK_DESCRIPTION());
                row.createCell(2).setCellValue(t.getTASK_STATUS());
                row.createCell(3).setCellValue(t.getTASK_ALLOCATION_DATE());
                row.createCell(4).setCellValue(t.getTASK_END_DATE());
                row.createCell(5).setCellValue(t.getUSER_ID());
                rowNum++;
            }
            byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
