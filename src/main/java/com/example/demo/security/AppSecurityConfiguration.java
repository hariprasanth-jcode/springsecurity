package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AppSecurityConfiguration {
  
	//To Define the Bean Methods
	
	//Security Filter Chain : public/protected api
	@Autowired
	JWTTokenFilter jwtTokenFilter;
	public SecurityFilterChain securityConfig(HttpSecurity httpSecurity) throws Exception {
		
		System.out.println("AppSecurityConfiguration : Configuring the security Rules :");
		
		httpSecurity
		.csrf(csrf->csrf.disable())
		.cors(csrf->csrf.disable())
		.authorizeHttpRequests(
				reqs->reqs.requestMatchers("/public/*").permitAll()
				.anyRequest()
				.authenticated()).addFilterBefore(this.jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
		return null;
	}
}
