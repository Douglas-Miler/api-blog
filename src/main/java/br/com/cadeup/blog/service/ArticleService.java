package br.com.cadeup.blog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadeup.blog.model.Article;
import br.com.cadeup.blog.repository.ArticleRepository;

@Service
public class ArticleService {

	private Long id;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	public Optional<Article> findByIdWithAuthor(String id) {
		
		this.id = Long.parseLong(id);
		
		return this.articleRepository.findByIdWithAuthor(this.id);
	}
	
}
