/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finlogic.task.service;

import com.finlogic.task.model.TasksUser;
import java.util.List;

/**
 *
 * @author disha
 */
public interface UserService {

    public TasksUser authenticate(String username, String password, String role);

    public List<TasksUser> getAllUsers();
}
