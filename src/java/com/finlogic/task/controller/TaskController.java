/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finlogic.task.controller;

import com.finlogic.task.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author disha
 */
@Controller
@RequestMapping("tasks.fin")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    ModelAndView modelAndView = new ModelAndView();

    @RequestMapping(params = "cmdAction=getUsers")
    public ModelAndView getUser() {
        modelAndView.addObject("users", taskService.users());
        modelAndView.setViewName("tasks");
        return modelAndView;
    }

}
