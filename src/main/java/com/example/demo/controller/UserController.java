package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserInformation;
import com.example.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userservice;
	
	@PostMapping("/public/user/signup")
	public String signUpUser(@RequestBody UserInformation info) {
		return userservice.signUpUser(info);
	}
	
	@PostMapping("/public/user/signin")
	public String signInUser(@RequestBody LoginDto login) {
		return userservice.signInUser(login);
	}
	
	//public/user/changepassword
	// public/user/delete/{userId}
}
