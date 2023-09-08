package com.bookstore.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.configuration.JwtRequestFilter;
import com.bookstore.dto.AuthenticationRequest;
import com.bookstore.dto.AuthenticationResponse;
import com.bookstore.entities.Token;
import com.bookstore.repositories.TokenRepository;
import com.bookstore.service.AuthService;
import com.bookstore.service.AuthServiceImpl;
import com.bookstore.util.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private AuthServiceImpl authServiceImpl;
	
	@Autowired
	TokenRepository tokenRepository;
	
	@Autowired
    private JwtUtil jwtUtil;
	
	@PostMapping("/auth")
	private AuthenticationResponse createAthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,HttpServletResponse response ) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException{
		
		 try {
	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
	        } catch (BadCredentialsException e) {
	            throw new BadCredentialsException("Incorrect username or password!");
	        } catch (DisabledException disabledException) {
	            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
	            return null;
	        }
		 final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		 
		 final String jwt = jwtUtil.generateToken(userDetails.getUsername());
//		 Token token = new Token();
//		 token.setToken(jwt);
//		 tokenRepository.save(token);
	     return new AuthenticationResponse(jwt);
		 
	}

}
	

		
	
	
	


