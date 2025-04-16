/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finlogic.task.repository.impl;

/**
 *
 * @author disha
 */
import com.finlogic.task.model.TasksUser;
import com.finlogic.task.repository.UserRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public TasksUser findUserByUsername(String username) {
        String sql = "SELECT USER_ID, USERNAME, PASSWORD, ROLE FROM TASKS_USER WHERE USERNAME = :USERNAME";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("USERNAME", username);

        List<TasksUser> users = namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(TasksUser.class));
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public List<TasksUser> findAllUsers() {
        String sql = "SELECT USER_ID, USERNAME, PASSWORD, ROLE FROM TASKS_USER WHERE ROLE != 'Admin'";
        return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TasksUser.class));
    }

}
