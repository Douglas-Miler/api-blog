package br.com.cadeup.blog.model;

import org.modelmapper.ModelMapper;
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
	
	public static CardDTO getInstance(Card card) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(card, CardDTO.class);
	}
	
}
