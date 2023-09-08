package com.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookstore.dto.AuthenticationResponse;
import com.bookstore.dto.SignupRequest;
import com.bookstore.dto.UserDTO;
import com.bookstore.entities.Token;
import com.bookstore.entities.User;
import com.bookstore.repositories.TokenRepository;
import com.bookstore.repositories.UserRepository;

import io.jsonwebtoken.Jwt;

@Service
public class AuthServiceImpl implements AuthService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public UserDTO createUser(SignupRequest signupRequest) {
		
		User user = new User();
		user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        User createdUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(createdUser.getId());
        userDTO.setName(createdUser.getName());
        userDTO.setEmail(createdUser.getEmail());
        return userDTO;
	}

//	@Override
//	public Token remove(AuthenticationResponse authenticationResponse) {
//		// TODO Auto-generated method stub
//		try {
//			authenticationResponse.wait(0);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return ;
//	}

//	@Override
//	public Token createJwt(AuthenticationResponse authenticationResponse) {
//		Token token = new Token();
//		token.setToken(authenticationResponse.getJwt());
//		Token createdToken =tokenRepository.save(token);
//		token.setId(createdToken.getId());
//		token.setToken(createdToken.getToken());
//		return token;
//	}
		
	}


