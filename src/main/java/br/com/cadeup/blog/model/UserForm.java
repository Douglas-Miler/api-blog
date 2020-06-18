package br.com.cadeup.blog.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserForm {

	private String email;
	private String password;
	
	public UsernamePasswordAuthenticationToken convertToUsernamePasswordAuthenticationToken() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}
	
}
