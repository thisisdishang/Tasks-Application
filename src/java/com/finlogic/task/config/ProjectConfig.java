/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finlogic.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

/**
 *
 * @author disha
 */

@Configuration
public class ProjectConfig {

    @Bean
    public JdbcTemplate jdbcTemplate() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tasksapplication", "root", "1234");
        SingleConnectionDataSource dataSource = new SingleConnectionDataSource(connection, true);
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedJdbcTemplate() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tasksapplication", "root", "1234");
        SingleConnectionDataSource dataSource = new SingleConnectionDataSource(connection, true);
        return new NamedParameterJdbcTemplate(dataSource);
    }

}
