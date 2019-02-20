/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.controllers;

import com.todo.dao.TaskDAO;
import com.todo.models.SuccessMessage;
import com.todo.models.Task;
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
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskDAO dao;

    /*create a Task*/
    @PostMapping("/task/create")
    public Task createTask(@Valid @RequestBody Task task) {
        return dao.save(task);
    }

    /*Get all Task*/
    @GetMapping("/task/all")
    public List<Task> getAll() {
        return dao.findAll();
    }

    /*Get a Task*/
    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") Long taskID) {

        Task tsk = dao.findOne(taskID);

        if (tsk == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(tsk);

    }

    /*Update a Task*/
    @PutMapping("/task/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable(value = "id") Long taskID, @Valid @RequestBody Task task) {

        Task tsk = dao.findOne(taskID);
        if (tsk == null) {
            return ResponseEntity.notFound().build();
        }
        tsk.setTaskTitle(task.getTaskTitle());
        tsk.setTodoDate(task.getTodoDate());
        tsk.setModifiedTimestamp(null);
        Task updateTask = dao.save(tsk);
        return ResponseEntity.ok().body(updateTask);

    }

    /*Delete a Task*/
    @DeleteMapping("/task/delete/{id}")
    public ResponseEntity<SuccessMessage> deleteTask(@PathVariable(value = "id") Long taskID) {

        Task tsk = dao.findOne(taskID);
        if (tsk == null) {
            return ResponseEntity.notFound().build();
        }
        dao.delete(taskID);
        SuccessMessage sm = new SuccessMessage();
        sm.setErrorCount(0);
        sm.setMessage("Task Deleted successfully");
        return ResponseEntity.ok().body(sm);

    }
}
