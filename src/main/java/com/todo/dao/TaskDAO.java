/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.dao;

import com.todo.models.Task;
import com.todo.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dlpme
 */
@Service
public class TaskDAO {

    @Autowired
    TaskRepository repository;

    // 1 Create Task
    public Task save(Task task) {
        return repository.save(task);
    }

    // 2 Search All
    public List<Task> findAll() {
        return (List<Task>) repository.findAll();
    }

    // 3 Search for one
    public Task findOne(Long id) {
        Optional<Task> tasks = repository.findById(id);
        if (tasks.isPresent()) {
            return tasks.get();
        } else {
            return null;
        }
    }

    // 4 Delete Task
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
