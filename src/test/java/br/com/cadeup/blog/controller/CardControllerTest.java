package br.com.cadeup.blog.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.cadeup.blog.config.exception.GlobalControllerExceptionHandler;

@SpringBootTest
public class CardControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private CardController controller;
	
	@Autowired
	private GlobalControllerExceptionHandler controllerAdvice;
	
	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders
						.standaloneSetup(controller)
						.setControllerAdvice(controllerAdvice)
						.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
						.build();
	}
	
	@Test
	public void testGetCardsSuccess() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/cards").param("size", "1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[0].*", hasSize(6)))
			.andDo(print());
	}
	
	@Test
	public void testGetSearchedCardsSuccess() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/cards/search").param("subject", "a"))
			.andExpect(status().isOk())
			.andDo(print());
	}
	
	@Test
	public void testGetSearchedCardsFail() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/cards/search"))
			.andExpect(status().isBadRequest())
			.andDo(print());
	}
	
}
