/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.controllers;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.dao.TaskStatusDAO;
import com.todo.models.SuccessMessage;
import com.todo.models.TaskStatus;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	ObjectMapper mapper = new ObjectMapper ();
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    TaskStatusDAO dao;

    /*create a Task*/
    @PostMapping("/create")
    public ResponseEntity<SuccessMessage> createTask(@Valid @RequestBody TaskStatus task) throws JsonProcessingException {
    	LOGGER.info("Incoming request for creating taskstatus [Body Object]: "+mapper.writeValueAsString(task));
    	return generateResponse("Created Successfully", true);
    }

    /*Get all Task*/
    @GetMapping("/all")
    public ResponseEntity<SuccessMessage> getAll() throws JsonGenerationException, JsonMappingException, IOException {
    	LOGGER.info("Incoming request for loading all task status");
    	List<TaskStatus> list=dao.findAll();
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectMapper mapper = new ObjectMapper();

       mapper.writeValue(out, list);

       byte[] data = out.toByteArray();

   	LOGGER.info("LoadAll Task status response : "+new String(data));
   	return generateResponse(list, true);
    }

    /*Get  status by id*/
    @GetMapping("/byid/{id}")
    public ResponseEntity<SuccessMessage> getTaskStatusById(@PathVariable(value = "id") Long id) throws JsonProcessingException {
    	LOGGER.info("Incoming request for loading task status by id : "+id);
        TaskStatus tsk = dao.findOne(id);

        if (tsk == null) {
        	return generateResponse("Not Found", false);
        }
        LOGGER.info("Task Status by ID Response : "+mapper.writeValueAsString(tsk));
        return generateResponse(tsk, true);

    }

    /*Get  status by task id*/
    @GetMapping("/bytaskid/{id}")
    public ResponseEntity<SuccessMessage> getByTaskId(@PathVariable(value = "id") Long id) throws JsonGenerationException, JsonMappingException, IOException {
    	LOGGER.info("Incoming request for loading task status by task id : "+id);
        List<TaskStatus> tsk = dao.findByTaskID(id);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectMapper mapper = new ObjectMapper();

       mapper.writeValue(out, tsk);

       byte[] data = out.toByteArray();

   	LOGGER.info("Task status response : "+new String(data));
   	return generateResponse(tsk, true);

    }

    /*Update a Task*/
    @PutMapping("/update/{id}")
    public ResponseEntity<SuccessMessage> updateTaskStatus(@PathVariable(value = "id") Long taskID, @Valid @RequestBody TaskStatus task) throws JsonProcessingException {
    	LOGGER.info("Update Task Status Request Body : "+mapper.writeValueAsString(task));
        TaskStatus tsk = dao.findOne(taskID);
        if (tsk == null) {
        	return generateResponse("Not Found", false);
        }
        tsk.setStatus(task.getStatus());
        TaskStatus updateTask = dao.save(tsk);
        return generateResponse(updateTask, true);

    }

    /*Delete a Task*/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SuccessMessage> deleteTaskStatus(@PathVariable(value = "id") Long taskID) {
    	LOGGER.info("Delete Task Status Request : "+taskID);
        TaskStatus tsk = dao.findOne(taskID);
        if (tsk == null) {
        	return generateResponse("Not Found", false);
        }
        String msg="";
        try {
        	dao.delete(taskID);
        	msg="Deleted Successfully";
        	LOGGER.info("Delete Task Status Deleted");
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
