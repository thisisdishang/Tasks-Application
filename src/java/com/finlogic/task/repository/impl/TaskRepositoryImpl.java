/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finlogic.task.repository.impl;

import com.finlogic.task.model.Tasks;
import com.finlogic.task.repository.TaskRepository;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author disha
 */

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TaskRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List getDashboardDetails() {
        String sql = "SELECT COUNT(TASK_ID) AS \"nTasksAllocated\",\n"
                + "SUM((CASE TASK_STATUS WHEN 1 THEN 1 ELSE 0 END)) AS \"nTasksPending\",\n"
                + "SUM((CASE TASK_STATUS WHEN 2 THEN 1 ELSE 0 END)) AS \"nTasksInProgress\",\n"
                + "SUM((CASE TASK_STATUS WHEN 3 THEN 1 ELSE 0 END)) AS \"nTasksCompleted\"\n"
                + "FROM TASKS_EXAMPLE;";
        return jdbcTemplate.queryForList(sql);
    }

//    @Override
//    public int insertTask(Tasks tasks) {
//        String sql = "INSERT INTO TASKS_EXAMPLE(TASK_ID,TASK_DESCRIPTION,TASK_STATUS,TASK_ALLOCATION_DATE,TASK_END_DATE,USER_ID,ALLOCATED_BY)\n"
//                + "VALUES(:TASK_ID, :TASK_DESCRIPTION, :TASK_STATUS, STR_TO_DATE(:TASK_ALLOCATION_DATE,'%Y-%m-%d'),STR_TO_DATE(:TASK_END_DATE,'%d-%m-%Y'), :USER_ID, :ALLOCATED_BY);";
//        return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(tasks));
//    }
    
    @Override
    public int insertTask(Tasks tasks) {
        String sql = "INSERT INTO TASKS_EXAMPLE(TASK_ID, TASK_DESCRIPTION, TASK_STATUS, TASK_ALLOCATION_DATE, TASK_END_DATE, USER_ID, ALLOCATED_BY) "
                + "VALUES (:TASK_ID, :TASK_DESCRIPTION, :TASK_STATUS, STR_TO_DATE(:TASK_ALLOCATION_DATE,'%Y-%m-%d'), "
                + "STR_TO_DATE(:TASK_END_DATE,'%Y-%m-%d'), :USER_ID, 'Admin');";

        return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(tasks));
    }

    @Override
    public List<Map<String, Object>> viewAllTask() {
        String sql = "SELECT t.TASK_ID, t.TASK_DESCRIPTION, "
                + "CASE WHEN t.TASK_STATUS = 1 THEN 'Pending' WHEN t.TASK_STATUS = 2 THEN 'In Progress' WHEN t.TASK_STATUS = 3 THEN 'Completed' END AS TASK_STATUS, "
                + "IFNULL(t.TASK_ALLOCATION_DATE, '-') AS TASK_ALLOCATION_DATE, "
                + "IFNULL(t.TASK_END_DATE, '-') AS TASK_END_DATE, "
                + "IFNULL(u.USERNAME, 'Not Assigned') AS ALLOCATED_TO "
                + "FROM TASKS_EXAMPLE t "
                + "LEFT JOIN TASKS_USER u ON t.USER_ID = u.USER_ID "
                + "ORDER BY t.TASK_END_DATE;";

        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> viewTasksByUser(String userId) {
        String sql = "SELECT t.TASK_ID, t.TASK_DESCRIPTION, "
                + "CASE WHEN t.TASK_STATUS = 1 THEN 'Pending' WHEN t.TASK_STATUS = 2 THEN 'In Progress' WHEN t.TASK_STATUS = 3 THEN 'Completed' END AS TASK_STATUS, "
                + "IFNULL(t.TASK_ALLOCATION_DATE, '-') AS TASK_ALLOCATION_DATE, "
                + "IFNULL(t.TASK_END_DATE, '-') AS TASK_END_DATE, "
                + "IFNULL(u.USERNAME, 'Not Assigned') AS ALLOCATED_TO "
                + "FROM TASKS_EXAMPLE t "
                + "LEFT JOIN TASKS_USER u ON t.USER_ID = u.USER_ID "
                + "WHERE t.USER_ID = :USER_ID "
                + "ORDER BY t.TASK_END_DATE;";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("USER_ID", userId);

        return namedParameterJdbcTemplate.queryForList(sql, params);
    }

    @Override
    public int deleteTask(Tasks task) {
        String sql = "DELETE FROM TASKS_EXAMPLE WHERE TASK_ID=:TASK_ID";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("TASK_ID", task.getTASK_ID());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public List viewTask(Tasks tasks) {
        String sql = "SELECT te.TASK_ID, te.TASK_DESCRIPTION, "
                + "CASE WHEN te.TASK_STATUS=1 THEN 'PENDING' "
                + "     WHEN te.TASK_STATUS=2 THEN 'INPROGRESS' "
                + "     WHEN te.TASK_STATUS=3 THEN 'COMPLETED' END AS TASK_STATUS, "
                + "IFNULL(te.TASK_ALLOCATION_DATE,'-') AS TASK_ALLOCATION_DATE, "
                + "IFNULL(te.TASK_END_DATE,'-') AS TASK_END_DATE, "
                + "te.USER_ID, tu.USERNAME "
                + "FROM TASKS_EXAMPLE te "
                + "JOIN TASKS_USER tu ON te.USER_ID = tu.USER_ID "
                + "WHERE te.TASK_ID = :TASK_ID";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("TASK_ID", tasks.getTASK_ID());

        return namedParameterJdbcTemplate.queryForList(sql, params);
    }

    @Override
    public int updateTask(Tasks task) {
        String sql = "UPDATE TASKS_EXAMPLE "
                + "SET TASK_DESCRIPTION=:TASK_DESCRIPTION, "
                + "TASK_STATUS=(CASE "
                + "WHEN :TASK_STATUS='PENDING' THEN '1' "
                + "WHEN :TASK_STATUS='INPROGRESS' THEN '2' "
                + "WHEN :TASK_STATUS='COMPLETED' THEN '3' "
                + "ELSE TASK_STATUS END), "
                + "TASK_END_DATE=COALESCE(:TASK_END_DATE, TASK_END_DATE), "
                + "USER_ID=COALESCE(:USER_ID, USER_ID) "
                + "WHERE TASK_ID=:TASK_ID";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("TASK_ID", task.getTASK_ID())
                .addValue("TASK_DESCRIPTION", task.getTASK_DESCRIPTION())
                .addValue("USER_ID", task.getUSER_ID())
                .addValue("TASK_END_DATE", task.getTASK_END_DATE())
                .addValue("TASK_STATUS", task.getTASK_STATUS());

        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public List<Map<String, Object>> getUserDashboardDetails(String userId) {
        String sql = "SELECT "
                + "(SELECT COUNT(*) FROM TASKS_EXAMPLE WHERE USER_ID = :USER_ID) AS nTasksAllocated, "
                + "(SELECT COUNT(*) FROM TASKS_EXAMPLE WHERE USER_ID = :USER_ID AND TASK_STATUS = '1') AS nTasksPending, "
                + "(SELECT COUNT(*) FROM TASKS_EXAMPLE WHERE USER_ID = :USER_ID AND TASK_STATUS = '2') AS nTasksInProgress, "
                + "(SELECT COUNT(*) FROM TASKS_EXAMPLE WHERE USER_ID = :USER_ID AND TASK_STATUS = '3') AS nTasksCompleted";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("USER_ID", userId);

        return namedParameterJdbcTemplate.queryForList(sql, params);
    }

    @Override
    public Tasks getTaskById(String taskId) {
        String sql = "SELECT TASK_ID, TASK_DESCRIPTION, TASK_STATUS, TASK_ALLOCATION_DATE, TASK_END_DATE, USER_ID, ALLOCATED_BY "
                + "FROM TASKS_EXAMPLE WHERE TASK_ID = :TASK_ID";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("TASK_ID", taskId);

        return namedParameterJdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(Tasks.class));
    }

    @Override
    public List<Tasks> exportToExcel(String userId) {
        String sql = "SELECT TASK_ID, TASK_DESCRIPTION, TASK_STATUS, TASK_ALLOCATION_DATE, TASK_END_DATE, USER_ID FROM TASKS_EXAMPLE";

        if (userId != null) {
            sql += " WHERE USER_ID = :USER_ID";
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        if (userId != null) {
            params.addValue("USER_ID", userId);
        }

        return namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(Tasks.class));
    }

}
