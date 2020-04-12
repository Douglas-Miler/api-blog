package br.com.cadeup.blog.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.cadeup.blog.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${blog.jwt.secret}")
	private String secret;
	
	public String generateToken(Authentication authentication) {
		
		Usuario usuario = (Usuario) authentication.getPrincipal();
		
		Date today = new Date();
		
		Date tokenExpiration = new Date(today.getTime() + 1800000L);
		
		return Jwts.builder()
				.setIssuer("API Blog")
				.setSubject(String.valueOf(usuario.getId()))
				.setIssuedAt(today)
				.setExpiration(tokenExpiration)
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isValidToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getUserId(String token) {
		return Long.parseLong(
				Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody()
				.getSubject());
	}
	
	
}
