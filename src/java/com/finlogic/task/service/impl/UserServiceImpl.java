/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finlogic.task.service.impl;

/**
 *
 * @author disha
 */
import com.finlogic.task.model.TasksUser;
import com.finlogic.task.repository.UserRepository;
import com.finlogic.task.service.UserService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public TasksUser authenticate(String username, String password, String role) {
        TasksUser user = userRepository.findUserByUsername(username);
        if (user != null && user.getPASSWORD().equals(password) && user.getROLE().equals(role)) {
            return user;
        }
        return null;
    }

    @Override
    public List<TasksUser> getAllUsers() {
        return userRepository.findAllUsers();
    }

}
