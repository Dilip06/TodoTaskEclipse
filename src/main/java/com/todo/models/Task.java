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
@Table(name = "task")
@EntityListeners(AuditingEntityListener.class)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long ID;

    @NotNull(message = "Please provide Task Title")
    private String TaskTitle;

    @NotNull(message = "Please provide Todo Date")
    private String TodoDate;

    private Timestamp CreatedTimestamp;

    private Timestamp ModifiedTimestamp;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTaskTitle() {
        return TaskTitle;
    }

    public void setTaskTitle(String TaskTitle) {
        this.TaskTitle = TaskTitle;
    }

    public String getTodoDate() {
        return TodoDate;
    }

    public void setTodoDate(String TodoDate) {
        this.TodoDate = TodoDate;
    }

    public Timestamp getCreatedTimestamp() {
        return CreatedTimestamp;
    }

    public void setCreatedTimestamp(Timestamp CreatedTimestamp) {
        this.CreatedTimestamp = CreatedTimestamp;
    }

    public Timestamp getModifiedTimestamp() {
        return ModifiedTimestamp;
    }

    public void setModifiedTimestamp(Timestamp ModifiedTimestamp) {
        this.ModifiedTimestamp = ModifiedTimestamp;
    }

    @Override
    public String toString() {
        return "Task{" + "TaskID=" + ID + ", TaskTitle=" + TaskTitle + ", TodoDate=" + TodoDate + ", CreatedTimestamp=" + CreatedTimestamp + ", ModifiedTimestamp=" + ModifiedTimestamp + '}';
    }

}
