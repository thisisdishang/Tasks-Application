/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finlogic.task.service.impl;

import com.finlogic.task.model.Tasks;
import com.finlogic.task.model.TasksFormBean;
import com.finlogic.task.repository.TaskRepository;
import com.finlogic.task.repository.impl.TaskRepositoryImpl;
import com.finlogic.task.service.TaskService;
import com.finlogic.task.util.ExportExcel;
import com.finlogic.task.util.TasksMapper;
import com.finlogic.util.datastructure.JSONParser;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author disha
 */
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    JSONParser jsonParser = new JSONParser();

    public TaskServiceImpl(TaskRepositoryImpl taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List getDashboardDetails() {
        return taskRepository.getDashboardDetails();
    }

    @Override
    public int insertTask(TasksFormBean tasks) {
        return taskRepository.insertTask(TasksMapper.toTasks(tasks));
    }

    @Override
    public List viewAllTask() {
        return taskRepository.viewAllTask();
    }

    @Override
    public String viewAllTaskGrid() {
        List data = taskRepository.viewAllTask();
        return jsonParser.parse(data, "TASK_ID", true, false);
    }

    @Override
    public String viewTasksByUserGrid(String userId) {
        List data = taskRepository.viewTasksByUser(userId);
        return jsonParser.parse(data, "TASK_ID", true, false);
    }

    @Override
    public int deleteTask(Tasks task) {
        return taskRepository.deleteTask(task);
    }

    @Override
    public List viewTask(Tasks tasks) {
        return taskRepository.viewTask(tasks);
    }

    @Override
    public int updateTask(Tasks tasks) {
        return taskRepository.updateTask(tasks);
    }

    @Override
    public List<Map<String, Object>> viewTasksByUser(String userId) {
        return taskRepository.viewTasksByUser(userId);
    }

    @Override
    public List<Map<String, Object>> getUserDashboardDetails(String userId) {
        return taskRepository.getUserDashboardDetails(userId);
    }

    @Override
    public Tasks getTaskById(String taskId) {
        return taskRepository.getTaskById(taskId);
    }

    @Override
    public byte[] exportToExcel(String userId) {
        ExportExcel exportExcel = new ExportExcel();
        try {
            List<Tasks> exportTask = taskRepository.exportToExcel(userId);
            return exportExcel.export(exportTask);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void importFromExcel(MultipartFile file) throws Exception {
        List<Tasks> tasksList = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0); // Get the first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            rowIterator.next(); // Skip the header row

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Tasks task = new Tasks();

                task.setTASK_ID(getStringCellValue(row.getCell(0)));
                task.setTASK_DESCRIPTION(getStringCellValue(row.getCell(1)));
                task.setTASK_STATUS(getStringCellValue(row.getCell(2)));
                task.setTASK_ALLOCATION_DATE(getStringCellValue(row.getCell(3)));
                task.setTASK_END_DATE(getStringCellValue(row.getCell(4)));
                task.setUSER_ID(getStringCellValue(row.getCell(5)));
                task.setALLOCATED_BY(getStringCellValue(row.getCell(6)));

                tasksList.add(task);
                System.out.println("Task Imported: " + task.getTASK_DESCRIPTION());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error processing Excel file: " + e.getMessage());
        }

        for (Tasks task : tasksList) {
            System.out.println("Importing Task: " + task.getTASK_DESCRIPTION());
            System.out.println("Allocated Date: " + task.getTASK_ALLOCATION_DATE());
            System.out.println("End Date: " + task.getTASK_END_DATE());
            System.out.println("User ID: " + task.getUSER_ID());

            int rowsInserted = taskRepository.insertTask(task);
            if (rowsInserted > 0) {
                System.out.println("✅ Task inserted successfully!");
            } else {
                System.out.println("❌ Task insertion failed!");
            }
        }

    }

    public String getStringCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    return dateFormat.format(cell.getDateCellValue());
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

}
