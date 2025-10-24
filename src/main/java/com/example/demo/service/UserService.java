package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.LoginDto;
import com.example.demo.entity.UserInformation;

@Service
public class UserService {

	@Autowired
	private UserJpaRepository userrepo;
	public String signUpUser(UserInformation info) {
		 Optional<UserInformation> user=userrepo.findById(info.getEmail());
		if(!user.isPresent()) {
			UserInformation detials=user.get();
			UserInformation user1=userrepo.save(info);
			return "Data saved Successfully"+user1.getFullname()+"!";
		}else {
		 return "Data is Not Existing";
		}
	}
	
	public String signInUser(LoginDto login) {
		Optional<UserInformation> user = userrepo.findByEmailAndPassword(login.getEmail(),login.getPassword());
	   if(user.isPresent()) {
		   return "user present "+user.get()+"!";
	   }else {
		   return "user does not exist";
	   }
	}
}
