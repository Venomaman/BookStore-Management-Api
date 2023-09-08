package com.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findFirstByEmail(String email);

}
