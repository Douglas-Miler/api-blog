package br.com.cadeup.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cadeup.blog.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{
	
}
