package br.com.cadeup.blog.controller;

import javax.validation.Valid;

import org.springframework.security.core.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadeup.blog.config.security.TokenService;
import br.com.cadeup.blog.model.TokenDTO;
import br.com.cadeup.blog.model.UsuarioDTO;

@RestController
@RequestMapping("/signin")
public class SignInController {
		
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<?> signin(@RequestBody @Valid UsuarioDTO form) {
		
		UsernamePasswordAuthenticationToken dadosLogin = form.convertToUsernamePasswordAuthenticationToken();
		
		try {
			
			Authentication authentication = authManager.authenticate(dadosLogin);
			
			String token = tokenService.generateToken(authentication);
			
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
			
		} catch(AuthenticationException ae) {
			return ResponseEntity.badRequest().build();
		}
	}
}
