package br.com.cadeup.blog.config.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.cadeup.blog.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	Logger logger = LoggerFactory.getLogger(TokenService.class);
	
	@Value("${blog.jwt.secret}")
	private String secret;
	
	public String generateToken(Authentication authentication) {
		
		logger.trace("Entering in generateToken method");
		
		User user = (User) authentication.getPrincipal();
		
		Date today = new Date();
		
		Date tokenExpiration = new Date(today.getTime() + 120000L);
		
		String token = Jwts.builder()
							.setIssuer("API Blog")
							.setSubject(String.valueOf(user.getId()))
							.claim("id", user.getId())
							.claim("userName", user.getName())
							.setIssuedAt(today)
							.setExpiration(tokenExpiration)
							.signWith(SignatureAlgorithm.HS256, secret).compact();
		
		return token;
	}

	public boolean isValidToken(String token) {
		
		logger.trace("Entering in isValidToken method");
		
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			
			logger.info(String.format("Token is valid? %b", true));
			
			return true;
		} catch (Exception e) {
			
			logger.error(String.format("Exception has occurred: %s", e.getMessage()));
			
			logger.error(String.format("Token is valid? %b", false));
			
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
