/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finlogic.task.service.impl;

import com.finlogic.task.repository.TaskRepository;
import com.finlogic.task.service.TaskService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author disha
 */
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List users() {
        return taskRepository.users();
    }

}
