package br.com.cadeup.blog.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.cadeup.blog.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{
	
	@Query("SELECT a FROM Article a JOIN a.user")
	Page<Article> findAllWithAuthor(Pageable pageable);
	
	@Query("SELECT a FROM Article a JOIN a.user WHERE a.id = ?1")
	Optional<Article> findByIdWithAuthor(Long id);
	
	@Query("SELECT a FROM Article a JOIN a.user WHERE a.content LIKE %?1%")
	Page<Article> findByContentWithAuthor(String searchedWord, Pageable pageable);
	
}
