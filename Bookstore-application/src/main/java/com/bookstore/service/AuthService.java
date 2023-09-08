package com.bookstore.service;

import com.bookstore.dto.AuthenticationResponse;
import com.bookstore.dto.SignupRequest;
import com.bookstore.dto.UserDTO;
import com.bookstore.entities.Token;

public interface AuthService {
	UserDTO createUser(SignupRequest signupRequest);

}
