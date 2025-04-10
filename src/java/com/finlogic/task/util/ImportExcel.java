/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finlogic.task.util;

import com.finlogic.task.model.Tasks;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ImportExcel {

    public static List<Tasks> parseExcel(MultipartFile file) throws IOException {
        List<Tasks> tasksList = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // Skip header row

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Tasks task = new Tasks();

            task.setTASK_ID(row.getCell(0).getStringCellValue());
            task.setTASK_DESCRIPTION(row.getCell(1).getStringCellValue());
            task.setTASK_STATUS(row.getCell(2).getStringCellValue());
            task.setTASK_ALLOCATION_DATE(row.getCell(3).getStringCellValue());
            task.setTASK_END_DATE(row.getCell(4).getStringCellValue());
            task.setUSER_ID(row.getCell(5).getStringCellValue());
            task.setALLOCATED_BY(row.getCell(6).getStringCellValue());

            tasksList.add(task);
        }

        workbook.close();
        return tasksList;
    }
}
