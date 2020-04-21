package br.com.cadeup.blog.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.cadeup.blog.model.User;
import br.com.cadeup.blog.repository.UserRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repository.findByEmail(username);
		
		if(user.isPresent())
			return user.get();
		
		throw new UsernameNotFoundException("Dados inválidos");
	}

	
	
}
