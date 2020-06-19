package br.com.cadeup.blog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.SortDefault.SortDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadeup.blog.model.Card;
import br.com.cadeup.blog.service.CardService;

@RestController
@RequestMapping("/cards")
public class CardController {
	
	Logger logger = LoggerFactory.getLogger(CardController.class);
	
	@Autowired 
	private CardService cardService;
	
	@GetMapping
	public List<Card> getCards(
			@PageableDefault(page = 0, size = 3)
			@SortDefaults({
							@SortDefault(sort = "id", direction = Direction.DESC)
						}) Pageable pageable){
		
		logger.trace("Entering in getCards method");
		logger.info("Success: 200");
		
		return cardService.getCardsFromArticles(pageable);
	}
	
	@GetMapping("/search")
	public List<Card> getSearchedCards(
			@PageableDefault(page = 0, size = 3)
			@SortDefaults({
							@SortDefault(sort = "id", direction = Direction.DESC)
						}) Pageable pageable, 
			@RequestParam(name="subject") String subject){

		logger.trace("Entering in getSearchedCards method");
		logger.info("Success: 200");
		
		return cardService.getCardsFromArticlesBySubject(subject, pageable);
	}
}
