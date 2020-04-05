package br.com.cadeup.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cadeup.blog.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
	
}
