package br.com.cadeup.blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter 
@ToString
public class TokenDTO {

	private String token;
	private String authenticationType;
	
}
