package br.com.cadeup.blog.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserForm {

	private String email;
	private String password;
	
	public UsernamePasswordAuthenticationToken convertToUsernamePasswordAuthenticationToken() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}
	
}
