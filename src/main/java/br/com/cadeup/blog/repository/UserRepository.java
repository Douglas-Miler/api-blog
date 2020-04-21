package br.com.cadeup.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cadeup.blog.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
	
}
