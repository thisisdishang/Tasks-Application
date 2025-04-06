/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finlogic.task.model;

import org.springframework.stereotype.Component;

/**
 *
 * @author disha
 */
@Component
public class Tasks {

    String TASK_ID, TASK_DESCRIPTION, TASK_STATUS, TASK_ALLOCATION_DATE, DATE, TASK_END_DATE, USER_ID, ALLOCATED_BY;

    public String getTASK_ID() {
        return TASK_ID;
    }

    public void setTASK_ID(String TASK_ID) {
        this.TASK_ID = TASK_ID;
    }

    public String getTASK_DESCRIPTION() {
        return TASK_DESCRIPTION;
    }

    public void setTASK_DESCRIPTION(String TASK_DESCRIPTION) {
        this.TASK_DESCRIPTION = TASK_DESCRIPTION;
    }

    public String getTASK_STATUS() {
        return TASK_STATUS;
    }

    public void setTASK_STATUS(String TASK_STATUS) {
        this.TASK_STATUS = TASK_STATUS;
    }

    public String getTASK_ALLOCATION_DATE() {
        return TASK_ALLOCATION_DATE;
    }

    public void setTASK_ALLOCATION_DATE(String TASK_ALLOCATION_DATE) {
        this.TASK_ALLOCATION_DATE = TASK_ALLOCATION_DATE;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getTASK_END_DATE() {
        return TASK_END_DATE;
    }

    public void setTASK_END_DATE(String TASK_END_DATE) {
        this.TASK_END_DATE = TASK_END_DATE;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getALLOCATED_BY() {
        return ALLOCATED_BY;
    }

    public void setALLOCATED_BY(String ALLOCATED_BY) {
        this.ALLOCATED_BY = ALLOCATED_BY;
    }

}
