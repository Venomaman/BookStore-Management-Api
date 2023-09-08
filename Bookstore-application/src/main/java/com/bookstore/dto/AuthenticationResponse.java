package com.bookstore.dto;


import lombok.Data;

@Data
public class AuthenticationResponse {
	
	private String jwt;
	
	


	public AuthenticationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthenticationResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	

}
