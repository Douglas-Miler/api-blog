package br.com.cadeup.blog.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadeup.blog.model.Article;
import br.com.cadeup.blog.model.ArticleDTO;
import br.com.cadeup.blog.model.ArticleForm;
import br.com.cadeup.blog.repository.ArticleRepository;
import br.com.cadeup.blog.service.ArticleService;

@RestController
public class ArticleController {

	Logger logger = LoggerFactory.getLogger(ArticleController.class);
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@GetMapping("/article/{id}")
	public ResponseEntity<?> getArticle(@PathVariable(name = "id") String id) {
	
		logger.trace("Entering in getArticle method");
		
		Optional<Article> article = articleService.findByIdWithAuthor(id);
		
		if(article.isPresent()) {
			logger.info("Article Found: 200");
			return ResponseEntity.ok(ArticleDTO.convertToArticleDTO(article.get()));
		}
		
		logger.info("Article Not Found: 400");
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody ArticleForm articleForm) {
		
		logger.trace("Entering in save method");
		
		this.articleRepository.save(new Article(articleForm));
		
		logger.info("Article has been saved: 200");
		
		return ResponseEntity.ok().build();
	}
}
