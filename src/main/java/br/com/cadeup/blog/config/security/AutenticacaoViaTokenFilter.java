package br.com.cadeup.blog.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.cadeup.blog.model.User;
import br.com.cadeup.blog.repository.UserRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UserRepository userRepo;

	public AutenticacaoViaTokenFilter(TokenService tokenService, UserRepository userRepo) {
		this.tokenService = tokenService;
		this.userRepo = userRepo;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = fetchToken(request);

		boolean isValid = tokenService.isValidToken(token);

		if (isValid)
			autenticarCliente(token);

		filterChain.doFilter(request, response);
	}

	private void autenticarCliente(String token) {

		Long userId = tokenService.getUserId(token);

		Optional<User> user = userRepo.findById(userId);

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.get(), null,
				null);

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String fetchToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");

		System.out.println(token);
		
		if (token == null || token.isEmpty() || !token.startsWith("Bearer "))
			return null;

		return token.substring(6);
	}

}
