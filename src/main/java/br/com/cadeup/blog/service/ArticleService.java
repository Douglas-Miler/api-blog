package br.com.cadeup.blog.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cadeup.blog.model.Article;
import br.com.cadeup.blog.repository.ArticleRepository;

@Service
public class ArticleService {

	Logger logger = LoggerFactory.getLogger(ArticleService.class);
	
	private Long id;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	public Optional<Article> findByIdWithAuthor(String id) {
		
		logger.trace("Entering in findByIdWithAuthor method");
		
		this.id = Long.parseLong(id);
		logger.trace("Successfull ID conversion to Long");
		
		return this.articleRepository.findByIdWithAuthor(this.id);
	}

	public List<Article> getArticlesFromSubject(String subject, Pageable pageable) {
		
		logger.trace("Entering in getArticlesFromSubject method");
		
		Page<Article> page = articleRepository.findByContentWithAuthor(this.removeWrappedSpaces(subject), pageable);
		return page.toList();
	}
	
	public List<Article> getPageableArticles(Pageable pageable) {
		
		logger.trace("Entering in getPageableArticles method");
		
		Page<Article> page = articleRepository.findAllWithAuthor(pageable);
		return page.toList();
	}
	
	private String removeWrappedSpaces(String subject) {
		return subject.trim();
	}
}
