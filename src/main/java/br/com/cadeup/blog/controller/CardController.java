package br.com.cadeup.blog.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadeup.blog.model.Article;
import br.com.cadeup.blog.model.Card;
import br.com.cadeup.blog.service.ArticleService;
import br.com.cadeup.blog.service.CardService;

@RestController
@RequestMapping("/cards")
public class CardController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired 
	private CardService cardService;
	
	@GetMapping
	public List<Card> getCards(
			@PageableDefault(page = 0, size = 3) Pageable pageable){
		
		List<Article> articles = articleService.getPageableArticles(pageable);
		
		return cardService.getCardsFromArticles(articles);
	}
	
	@GetMapping("/search")
	public List<Card> getSearchedCards(
			@PageableDefault(page = 0, size = 3) Pageable pageable, 
			@RequestParam(name="subject") String subject){
		
		List<Article> articles = articleService.getArticlesFromSubject(subject, pageable);
		
		if(!articles.isEmpty())
			return cardService.getCardsFromArticles(articles);
		
		return new ArrayList<>();
	}
}
