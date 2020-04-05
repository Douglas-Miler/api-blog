package br.com.cadeup.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadeup.blog.model.Card;
import br.com.cadeup.blog.model.CardDTO;
import br.com.cadeup.blog.repository.CardRepository;

@RestController
@RequestMapping("/cards")
public class CardController {
	
	@Autowired
	private CardRepository cardRepo;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	public List<CardDTO> getCards(){
		List<CardDTO> cardsDto = new ArrayList<>();
		
		List<Card> cards = cardRepo.findAll();
		
		for (Card card : cards) {
			cardsDto.add(CardDTO.getInstance(card));
		}
		
		return cardsDto;
	}
	
}
