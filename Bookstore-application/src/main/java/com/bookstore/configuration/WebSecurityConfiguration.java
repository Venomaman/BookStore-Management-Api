package com.bookstore.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive;

import jakarta.servlet.http.Cookie;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfiguration{
	

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	 @Bean
	 public PasswordEncoder passwordEncoder() {
	      return new BCryptPasswordEncoder();
	    }
	 
//	 @Autowired
//	 private LogoutHandler logoutHandler;
	 
	 
	 @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		 return httpSecurity.csrf().disable()
				 .authorizeHttpRequests()
	             .requestMatchers("/books","/addbook","/books/{id}","/book/{bookName}","/Books/{author}","/getOrder","/register","/auth").permitAll()
	             .and()
	             .authorizeHttpRequests().requestMatchers("/**")
	             .authenticated().and()
	            
	             .sessionManagement()
	             .sessionCreationPolicy(SessionCreationPolicy.STATELESS)       
	             .and()
	             .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
	             .logout(logout -> logout
	                     .logoutUrl("/logout")
	                     .addLogoutHandler((request, response, auth) -> {
	                         for (Cookie cookie : request.getCookies()) {
	                             String cookieName = cookie.getName();
	                             Cookie cookieToDelete = new Cookie(cookieName,null);
	                             cookieToDelete.setMaxAge(0);
	                             response.addCookie(cookieToDelete);
	                         }
	                     }))
	         //    .logoutSuccessHandler((request, response, authentication) ->SecurityContextHolder.clearContext()).and()
	             .build();
		         
				 
	 }
	 

	 
	 @Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		 return configuration.getAuthenticationManager();
		 
	 }

}
