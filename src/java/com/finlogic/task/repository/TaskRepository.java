/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finlogic.task.repository;

import com.finlogic.task.model.Tasks;
import java.util.List;
import java.util.Map;

/**
 *
 * @author disha
 */

public interface TaskRepository {

    List getDashboardDetails();

    int insertTask(Tasks tasks);

//    int insertTask(Tasks tasks);
    
    List<Map<String, Object>> viewAllTask();

    List<Map<String, Object>> viewTasksByUser(String userId);

    int deleteTask(Tasks task);

    List viewTask(Tasks tasks);

    int updateTask(Tasks task);

    List<Map<String, Object>> getUserDashboardDetails(String userId);

    Tasks getTaskById(String taskId);

    List<Tasks> exportToExcel(String userId);

}
