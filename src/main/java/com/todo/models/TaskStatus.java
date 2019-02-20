/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.models;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author dlpme
 */
@Entity

@Table(name = "task_status")
@EntityListeners(AuditingEntityListener.class)
public class TaskStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    @NotNull(message = "Please provide Task ID")
    private long task_id;

    @NotNull(message = "Please provide Task Status")
    private String status;

    private Timestamp status_timestamp;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getTask_id() {
        return task_id;
    }

    public void setTask_id(long task_id) {
        this.task_id = task_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getStatus_timestamp() {
        return status_timestamp;
    }

    public void setStatus_timestamp(Timestamp status_timestamp) {
        this.status_timestamp = status_timestamp;
    }

    @Override
    public String toString() {
        return "TaskStatus{" + "ID=" + ID + ", TaskID=" + task_id + ", status=" + status + ", status_timestamp=" + status_timestamp + '}';
    }

}
