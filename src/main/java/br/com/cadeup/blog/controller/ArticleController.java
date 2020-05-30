package br.com.cadeup.blog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.cadeup.blog.model.Article;
import br.com.cadeup.blog.model.ArticleDTO;
import br.com.cadeup.blog.model.ArticleForm;
import br.com.cadeup.blog.service.ArticleService;

@RestController
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@GetMapping("/article/{id}")
	public ResponseEntity<?> getArticle(@PathVariable(name = "id") String id) {
		Optional<Article> article = articleService.findByIdWithAuthor(id);
		
		if(article.isPresent())
			return ResponseEntity.ok(ArticleDTO.convertToArticleDTO(article.get()));
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/save/image")
	public ResponseEntity<?> save(@RequestBody MultipartFile imageFile){
		System.out.println(imageFile.getOriginalFilename());
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/save/form")
	public ResponseEntity<?> save(@RequestBody ArticleForm articleForm){
		return ResponseEntity.ok().build();
	}
}
