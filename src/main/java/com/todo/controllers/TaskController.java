/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.dao.TaskDAO;
import com.todo.models.SuccessMessage;
import com.todo.models.Task;
import com.todo.models.TaskStatus;

import java.io.ByteArrayOutputStream;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author dlpme
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {
	ObjectMapper mapper = new ObjectMapper ();
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    TaskDAO dao;
    
    /*create a Task*/
    @PostMapping("/task/create")
    public ResponseEntity<SuccessMessage> createTask(@Valid @RequestBody Task task) throws JsonProcessingException {
    	
    	LOGGER.info("Incoming request for creating task [Body Object]: "+mapper.writeValueAsString(task));
    	return generateResponse("Created Successfully", true);
    }

    /*Get all Task*/
    @GetMapping("/task/all")
    public ResponseEntity<SuccessMessage> getAll()throws Exception {
    	LOGGER.info("LoadAll Task request");
    	List<Task> list=dao.findAll();
    	 ByteArrayOutputStream out = new ByteArrayOutputStream();
         ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(out, list);

        byte[] data = out.toByteArray();

    	LOGGER.info("LoadAll Task response : "+new String(data));
    	       
        return generateResponse(list, true);
    }

    /*Get a Task*/
    @GetMapping("/task/{id}")
    public ResponseEntity<SuccessMessage> getTaskById(@PathVariable(value = "id") Long taskID)throws JsonProcessingException {
    	LOGGER.info("Request for Find One Task by ID : "+taskID);
        Task tsk = dao.findOne(taskID);

        if (tsk == null) {
        	return generateResponse("Not Found", false);
        }
        LOGGER.info("Response for Find One Task by ID : "+mapper.writeValueAsString(tsk));

        return generateResponse(tsk, true);

    }

    /*Update a Task*/
    @PutMapping("/task/update/{id}")
    public ResponseEntity<SuccessMessage> updateTask(@PathVariable(value = "id") Long taskID, @Valid @RequestBody Task task) throws JsonProcessingException {
    	LOGGER.info("Update Request body : "+mapper.writeValueAsString(task));
        Task tsk = dao.findOne(taskID);
        if (tsk == null) {
        	return generateResponse("Not Found", false);
        }
        tsk.setTaskTitle(task.getTaskTitle());
        tsk.setTodoDate(task.getTodoDate());
        tsk.setModifiedTimestamp(null);
        
        Task updateTask = dao.save(tsk);
        
        return generateResponse(updateTask, true);
        

    }

    /*Delete a Task*/
    @DeleteMapping("/task/delete/{id}")
    public ResponseEntity<SuccessMessage> deleteTask(@PathVariable(value = "id") Long taskID) {
    	LOGGER.info("Task Delete Request task ID : "+taskID);
        Task tsk = dao.findOne(taskID);
        if (tsk == null) {
        	return generateResponse("Not Found", false);
        }
        String msg="";
        try {
        	dao.delete(taskID);
        	msg="Deleted Successfully";
        	return generateResponse(msg, true);
		} catch (Exception e) {
			msg=e.getMessage();
			return generateResponse(msg, false);
		}
        
        

    }
   private ResponseEntity<SuccessMessage> generateResponse(Object obj, boolean isSuccess) {
	   SuccessMessage sm = new SuccessMessage();
	   if (isSuccess) {
			   sm.setErrorCount(0);
			   sm.setMessage("Success");
	   }else {
		   sm.setErrorCount(1);
	       sm.setMessage("Failed");
	   }
       sm.setResponseObject(obj);
       return ResponseEntity.ok().body(sm);
	   
   }
}
