/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finlogic.task.repository;

import com.finlogic.task.model.TasksUser;
import java.util.List;

/**
 *
 * @author disha
 */
public interface UserRepository {

    public TasksUser findUserByUsername(String username);

    public List<TasksUser> findAllUsers();
}
