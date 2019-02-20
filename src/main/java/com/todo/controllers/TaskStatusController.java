/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.controllers;

import com.todo.dao.TaskStatusDAO;
import com.todo.models.SuccessMessage;
import com.todo.models.TaskStatus;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dlpme
 */
@RestController
@RequestMapping("/status")
public class TaskStatusController {

    @Autowired
    TaskStatusDAO dao;

    /*create a Task*/
    @PostMapping("/create")
    public TaskStatus createTask(@Valid @RequestBody TaskStatus task) {
        return dao.save(task);
    }

    /*Get all Task*/
    @GetMapping("/all")
    public List<TaskStatus> getAll() {
        return dao.findAll();
    }

    /*Get  status by id*/
    @GetMapping("/byid/{id}")
    public ResponseEntity<TaskStatus> getTaskStatusById(@PathVariable(value = "id") Long id) {

        TaskStatus tsk = dao.findOne(id);

        if (tsk == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(tsk);

    }

    /*Get  status by task id*/
    @GetMapping("/bytaskid/{id}")
    public List<TaskStatus> getByTaskId(@PathVariable(value = "id") Long id) {

        List<TaskStatus> tsk = dao.findByTaskID(id);
        return tsk;

    }

    /*Update a Task*/
    @PutMapping("/update/{id}")
    public ResponseEntity<TaskStatus> updateTaskStatus(@PathVariable(value = "id") Long taskID, @Valid @RequestBody TaskStatus task) {

        TaskStatus tsk = dao.findOne(taskID);
        if (tsk == null) {
            return ResponseEntity.notFound().build();
        }
        tsk.setStatus(task.getStatus());
        TaskStatus updateTask = dao.save(tsk);
        return ResponseEntity.ok().body(updateTask);

    }

    /*Delete a Task*/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SuccessMessage> deleteTaskStatus(@PathVariable(value = "id") Long taskID) {

        TaskStatus tsk = dao.findOne(taskID);
        if (tsk == null) {
            return ResponseEntity.notFound().build();
        }
        dao.delete(taskID);
        SuccessMessage sm = new SuccessMessage();
        sm.setErrorCount(0);
        sm.setMessage("Task status Deleted successfully");
        return ResponseEntity.ok().body(sm);

    }
}
