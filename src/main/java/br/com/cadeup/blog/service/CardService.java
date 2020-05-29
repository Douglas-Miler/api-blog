package br.com.cadeup.blog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cadeup.blog.model.Article;
import br.com.cadeup.blog.model.Card;

@Service
public class CardService {

	@Autowired
	private ArticleService articleService;
	
	public List<Card> getCardsFromArticles(Pageable pageable) {
		return articleService.getPageableArticles(pageable)
				.stream()
				.map(elto -> Card.convertToCard(elto))
				.collect(Collectors.toList());
	}

	public List<Card> getCardsFromArticlesBySubject(String subject, Pageable pageable) {
		List<Article> articles = articleService.getArticlesFromSubject(subject, pageable);
		return this.getCardsMappedByArticles(articles);
	}

	private List<Card> getCardsMappedByArticles(List<Article> articles) {
		if(!articles.isEmpty())
			return articles
					.stream()
					.map(elto -> Card.convertToCard(elto))
					.collect(Collectors.toList());
		
		return new ArrayList<>();
	}
}