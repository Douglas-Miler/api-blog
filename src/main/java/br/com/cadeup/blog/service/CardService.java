package br.com.cadeup.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.cadeup.blog.model.Article;
import br.com.cadeup.blog.model.Card;

@Service
public class CardService {

	public List<Card> getCardsFromArticles(List<Article> articles) {
		return articles.stream().map(elto -> Card.convertToCard(elto)).collect(Collectors.toList());
	}
}
