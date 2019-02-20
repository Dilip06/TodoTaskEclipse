/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.repository;

import com.todo.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author dlpme
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

}
