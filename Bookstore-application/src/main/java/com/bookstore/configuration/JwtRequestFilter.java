package com.bookstore.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bookstore.service.jwt.UserDetailsServiceImpl;
import com.bookstore.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	public static String CURRENT_USER="";
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, java.io.IOException {
	
		final String header=request.getHeader("Authorization");
		
		String jwtToken = null;
		String userName = null;
		
		if(header != null && header.startsWith("Bearer ")) {
			jwtToken=header.substring(7);
			
			try {
				
				userName=jwtUtil.extractUsername(jwtToken);
				CURRENT_USER = userName;
				
			}catch(IllegalArgumentException e) {
				System.out.println("unable to get token");
			}catch(ExpiredJwtException e) {
				System.out.println("jwt token expired");
			}
		}else {
			System.out.println("jwt doest not strt with bearer");
		}
		
		if(userName != null && SecurityContextHolder.getContext().getAuthentication()==null) {
		UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userName);
		
		if(jwtUtil.validateToken(jwtToken, userDetails)) {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			
			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
		}
		
		filterChain.doFilter(request, response);
		

}
	
	

}
