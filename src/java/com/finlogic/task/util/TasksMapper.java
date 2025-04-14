/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finlogic.task.util;

import com.finlogic.task.model.Tasks;
import com.finlogic.task.model.TasksFormBean;
import java.time.LocalDate;
import java.util.UUID;

/**
 *
 * @author njuser
 */
public class TasksMapper {

    public static Tasks toTasks(TasksFormBean tasksFormBean) {
        Tasks tasks = new Tasks();
        tasks.setTASK_ID(UUID.randomUUID().toString());
        tasks.setTASK_DESCRIPTION(tasksFormBean.getTaskDescription());

        if (tasksFormBean.getTaskEndDate() != null && !tasksFormBean.getTaskEndDate().isEmpty()) {
            tasks.setTASK_END_DATE(tasksFormBean.getTaskEndDate());
        } else {
            tasks.setTASK_END_DATE("2025-12-31");
        }

        tasks.setTASK_STATUS("1");
        tasks.setUSER_ID(tasksFormBean.getTaskUserId());
        tasks.setTASK_ALLOCATION_DATE(LocalDate.now().toString());

        return tasks;
    }

}
