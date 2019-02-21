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

public class TaskTestCases extends AbstractTest{
	@Override
	   @Before
	   public void setUp() {
	      super.setUp();
	   }
	@Test
	public void getAllTask() throws Exception {
	   String uri = "/tasks/task/all";
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   SuccessMessage sm = super.mapFromJson(content, SuccessMessage.class);
	   assertEquals(sm.getErrorCount(), 0,1);
	}
	
	@Test
	   public void createTask() throws Exception {
	      String uri = "/tasks/task/create";
	      Task task = new Task();
	      task.setTaskTitle("Test Task");
	      task.setTodoDate("2019-02-25");
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
	   public void updateTask() throws Exception {
	      String uri = "/tasks/task/update/6";
	      Task task = new Task();
	      task.setTaskTitle("Updating Test Task Title");
	      task.setTodoDate("2019-02-25");
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
	   public void deleteTask() throws Exception {
	      String uri = "/tasks/task/delete/18";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      SuccessMessage sm = super.mapFromJson(content, SuccessMessage.class);
		   assertEquals(sm.getErrorCount(), 0,1);
	   }
}
