package br.com.cadeup.blog.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadeup.blog.model.Article;
import br.com.cadeup.blog.model.Card;
import br.com.cadeup.blog.repository.ArticleRepository;

@RestController
@RequestMapping("/cards")
public class CardController {
	
	@Autowired
	private ArticleRepository articleRepo;
	
	@GetMapping
	public List<Card> getCards(
			@PageableDefault(page = 0, size = 3) Pageable pageable){
		
		Page<Article> page = articleRepo.findAll(pageable);
		
		List<Article> articles = page.toList();
		
		return articles.stream().map(elto -> Card.convertToCard(elto)).collect(Collectors.toList());
	}
	
}
