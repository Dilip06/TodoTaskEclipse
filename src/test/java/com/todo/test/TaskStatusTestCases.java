package com.todo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.todo.models.SuccessMessage;
import com.todo.models.Task;
import com.todo.models.TaskStatus;

public class TaskStatusTestCases extends AbstractTest{
	@Override
	   @Before
	   public void setUp() {
	      super.setUp();
	   }
	@Test
	public void getAllTask() throws Exception {
	   String uri = "/status/all";
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   SuccessMessage sm = super.mapFromJson(content, SuccessMessage.class);
	   assertEquals(sm.getErrorCount(), 0,1);
	}
	
	@Test
	   public void createTaskStatus() throws Exception {
	      String uri = "/status/create";
	      TaskStatus task = new TaskStatus();
	      task.setTask_id(7);
	      task.setStatus("Testing");
	      String inputJson = super.mapToJson(task);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      SuccessMessage sm = super.mapFromJson(content, SuccessMessage.class);
		   assertEquals(sm.getErrorCount(), 0,1);
	   }
	   @Test
	   public void updateTaskStatus() throws Exception {
	      String uri = "/status/update/6";
	      TaskStatus task = new TaskStatus();
	      task.setTask_id(7);
	      task.setStatus("Testing");
	      String inputJson = super.mapToJson(task);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      SuccessMessage sm = super.mapFromJson(content, SuccessMessage.class);
		   assertEquals(sm.getErrorCount(), 0,1);
	   }
	   @Test
	   public void deleteTaskStatus() throws Exception {
	      String uri = "/status/delete/18";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      SuccessMessage sm = super.mapFromJson(content, SuccessMessage.class);
		   assertEquals(sm.getErrorCount(), 0,1);
	   }
}
