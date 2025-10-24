package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.JWTUtil;

@RestController
public class JwtController {

	@Autowired
	private JWTUtil jwtUtil;
	
	@GetMapping("/jwt/create/token/{email}")
	public String test(@PathVariable String email) {
		return jwtUtil.createToken(email);
}
	@GetMapping("/jwt/validate/token")
	public Boolean validateToken() {
		return jwtUtil.validateToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoYXJpLmhwMDAwQGdtYWlsLmNvbSIsImlhdCI6MTc2MDU5OTM2MSwiZXhwIjoxNzYwNTk5NjYxfQ.S85WadysxBr9POZepDEMtblYf9q9dY982dyEIXjp6kM", "hari.hp000@gmail.com");
	}
	
}
