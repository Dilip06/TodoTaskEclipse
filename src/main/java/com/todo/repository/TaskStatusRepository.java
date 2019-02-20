/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.repository;

import com.todo.models.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author dlpme
 */
public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {

//    @Query("SELECT t FROM task_status t where t.task_id = :taskid")
//    public List<TaskStatus> findByTaskID(@Param("taskid") long taskid);
}
