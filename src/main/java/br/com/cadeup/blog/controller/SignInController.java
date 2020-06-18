package br.com.cadeup.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadeup.blog.config.security.TokenService;
import br.com.cadeup.blog.model.TokenDTO;
import br.com.cadeup.blog.model.UserForm;

@RestController
@RequestMapping("/signin")
public class SignInController {
		
	Logger logger = LoggerFactory.getLogger(SignInController.class);
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<?> signin(@RequestBody UserForm form) {
		
		logger.trace("Entering in signin method");
		
		UsernamePasswordAuthenticationToken dadosLogin = form.convertToUsernamePasswordAuthenticationToken();
		
		logger.info("UserForm converted to UsernamePasswordAuthenticationToken object");
		
		try {
			
			Authentication authentication = authManager.authenticate(dadosLogin);
			
			logger.info("User has been authenticated");
			
			String token = tokenService.generateToken(authentication);
			
			logger.info(String.format("Generated token: %s", token));
			
			logger.info("Success: 200");
			
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
			
		} catch(AuthenticationException ae) {
			logger.error(String.format("AuthenticationException has occurred: %s", ae.getMessage()));
			logger.info("Bad Request: 400");
			
			return ResponseEntity.badRequest().build();
		}
	}
}
