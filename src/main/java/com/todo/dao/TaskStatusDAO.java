/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.dao;

import com.todo.models.TaskStatus;
import com.todo.repository.TaskStatusRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dlpme
 */
@Service
public class TaskStatusDAO {

    Logger logger = LogManager.getLogger(TaskStatusDAO.class);
    @Autowired
    TaskStatusRepository repository;

    @PersistenceContext
    EntityManager entityManager;

    public List<TaskStatus> findByTaskID(Long id) {
        Query query = entityManager.createNativeQuery("SELECT t.* FROM task_status t where t.task_id = ?", TaskStatus.class);
        query.setParameter(1, id);
        return query.getResultList();
    }

    // 1 Create Task status
    public TaskStatus save(TaskStatus task) {
        return repository.save(task);
    }

    // 2 Search All task status
    public List<TaskStatus> findAll() {
        return (List<TaskStatus>) repository.findAll();
    }

    // 3 Search for one task status
    public TaskStatus findOne(Long id) {
        Optional<TaskStatus> tasks = repository.findById(id);
        if (tasks.isPresent()) {
            return tasks.get();
        } else {
            return null;
        }
    }
//
//    //4  load all task status by task id
//    public List<TaskStatus> findByTaskID(Long id) {
//        return repository.findByTaskID(id);
//
//    }

    // 5 Delete Task status by id
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
