package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.dto.SignupRequest;
import com.bookstore.dto.UserDTO;
import com.bookstore.service.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class SignupController {
	
    @Autowired
	private AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody SignupRequest signupRequest){
    	UserDTO createdUser = authService.createUser(signupRequest);
    	if(createdUser == null)
    		return new ResponseEntity<>("user not created ", HttpStatus.BAD_REQUEST);	
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    	
    }
	
    }
    
	


