package br.com.cadeup.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
	
	@CrossOrigin(origins = "https://demo-angular-blog.herokuapp.com")
	@GetMapping
	public List<CardDTO> getCards(@PageableDefault(page = 0, size = 4) Pageable pageable){
		
		Page<Card> cards = cardRepo.findAll(pageable);
		
		return CardDTO.convert(cards);
	}
	
}
