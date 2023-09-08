package com.bookstore.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.entities.Token;

public interface TokenRepository extends JpaRepository<Token, Long>{
	
	Optional<Token> findByToken(String token);

}
