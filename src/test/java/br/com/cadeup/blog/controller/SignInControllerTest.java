package br.com.cadeup.blog.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cadeup.blog.config.exception.GlobalControllerExceptionHandler;
import br.com.cadeup.blog.model.UserForm;

@SpringBootTest
public class SignInControllerTest {

	@Autowired
	private SignInController controller;
	
	@Autowired
	private GlobalControllerExceptionHandler controllerAdvice;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders
							.standaloneSetup(controller)
							.setControllerAdvice(controllerAdvice)
							.build();
	}
	
	@Test
	public void testSignInFail() throws Exception {
		
		this.mockMvc
		.perform(MockMvcRequestBuilders
					.post("/signin")
					.content("")
					.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andDo(print());
	}
	
	@Test
	public void testSignInSuccess() throws Exception {
		
		String userFormSerialized = getUserFormSerialized();
		
		this.mockMvc
			.perform(
					MockMvcRequestBuilders
						.post("/signin")
						.content(userFormSerialized)
						.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(print());
	}
	
	private String getUserFormSerialized() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(new UserForm("julinho@server.com", "12345"));
	}

	
}
