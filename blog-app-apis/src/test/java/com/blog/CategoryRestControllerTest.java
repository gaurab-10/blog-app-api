package com.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.blog.controllers.CategoryController;
import com.blog.services.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest
class CategoryRestControllerTest {

	@MockBean // similar to @Autowired
	private CategoryService categoryService;

	@Autowired
	private MockMvc mockMvc; // mock mvc is used to send the request.

	@Test
	void getAllCategoriesTest() throws Exception {
	

		// creating the http uri for get request....
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/categories/");
		// performing the request builder
		ResultActions perform = mockMvc.perform(requestBuilder);
		// Storing the result
		MvcResult mvcResult = perform.andReturn();
		log.info("Mvc Result ---> {}", mvcResult);

		// gettting the response from the result.
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}

}
