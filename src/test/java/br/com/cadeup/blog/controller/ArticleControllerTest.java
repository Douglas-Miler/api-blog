package br.com.cadeup.blog.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

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
import br.com.cadeup.blog.model.ArticleForm;

@SpringBootTest
public class ArticleControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private ArticleController controller;
	
	@Autowired
	private GlobalControllerExceptionHandler controllerAdvice;
	
	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders
						.standaloneSetup(controller)
						.setControllerAdvice(controllerAdvice)
						.build();
	}
	
	
	@Test
	public void testGetArticleNotFound() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/article/-2"))
			.andExpect(status().isNotFound())
			.andDo(print());
	}
	
	@Test
	public void testGetArticleBadRequest() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/article/A"))
			.andExpect(status().isBadRequest())
			.andDo(print());
	}
	
	@Test
	public void testGetArticleSuccess() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/article/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.title").exists())
			.andExpect(jsonPath("$.secondaryTitle").exists())
			.andExpect(jsonPath("$.category").exists())
			.andExpect(jsonPath("$.introduction").exists())
			.andExpect(jsonPath("$.content").exists())
			.andExpect(jsonPath("$.creationDate").exists())
			.andExpect(jsonPath("$.name").exists())
			.andExpect(jsonPath("$.position").exists())
			.andExpect(jsonPath("$.resume").exists())
			.andExpect(jsonPath("$.linkedInURL").exists())
			.andExpect(jsonPath("$.profileImage").exists())
			.andDo(print());
	
	}
	
	@Test
	public void testSaveFail() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders
						.post("/save")
						.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andDo(print());
		
		this.mockMvc
			.perform(MockMvcRequestBuilders
						.post("/save")
						.contentType(MediaType.APPLICATION_JSON)
						.content(""))
			.andExpect(status().isBadRequest())
			.andDo(print());
	}
	
	@Test
	public void testSaveSuccess() throws Exception {
		
		String articleFormSerializedToString = trySerialization();
		
		this.mockMvc
			.perform(MockMvcRequestBuilders
						.post("/save")
						.contentType(MediaType.APPLICATION_JSON)
						.content(articleFormSerializedToString))
			.andExpect(status().isOk())
			.andDo(print());
	}

	private String trySerialization() {

		String objectSerialized = null;
		
		try {
			objectSerialized = serialize();
		} catch(JsonProcessingException jpe) {
			jpe.printStackTrace();
		}
		
		return objectSerialized;
	}

	private String serialize() throws JsonProcessingException {
		ArticleForm articleForm = getArticleFormInstance();

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(articleForm);
	}
	
	private ArticleForm getArticleFormInstance() {
		return ArticleForm
					.builder()
					.category("Tests")
					.content("Tests")
					.creationDate(LocalDate.now())
					.image("some hash value")
					.introduction("This is a test")
					.secondaryTitle("Pay attention! This is a TEST")
					.title("Test")
					.userId((long) 1)
					.build();
					
	}
	
}
