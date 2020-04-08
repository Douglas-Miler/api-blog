package br.com.cadeup.blog.model;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Component
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CardDTO {

	private String description;
	private String title;
	private String url;
	
	private static CardDTO getInstance(Card card) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(card, CardDTO.class);
	}
	
	public static List<CardDTO> convert(Page<Card> card){
		return card.map(element -> CardDTO.getInstance(element)).toList();
	}
}
