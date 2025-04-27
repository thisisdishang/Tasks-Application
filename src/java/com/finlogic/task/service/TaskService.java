/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finlogic.task.service;

import com.finlogic.task.model.Tasks;
import com.finlogic.task.model.TasksFormBean;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author disha
 */

public interface TaskService {

    List getDashboardDetails();

    int insertTask(TasksFormBean tasks);

    List viewAllTask();

    String viewAllTaskGrid();

    String viewTasksByUserGrid(String userId);

    int deleteTask(Tasks task);

    List viewTask(Tasks tasks);

    int updateTask(Tasks tasks);

    List<Map<String, Object>> viewTasksByUser(String userId);

    List<Map<String, Object>> getUserDashboardDetails(String userId);

    Tasks getTaskById(String taskId);

    byte[] exportToExcel(String userId);

    void importFromExcel(MultipartFile file) throws Exception;

    String getStringCellValue(Cell cell);

}
