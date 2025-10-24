package com.example.demo.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.entity.UserInformation;
import com.example.demo.service.UserJpaRepository;

@Component
public class SecurityUserAunthenticationService implements UserDetailsService{

	@Autowired
	UserJpaRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("SecurityUserAunthenticationService: loading data from DB of user:"+emailId);
		Optional<UserInformation> userInfo=userRepository.findById(emailId);
		
		if(userInfo.isPresent()) {
			return userInfo.get();
		}else {
			throw new UsernameNotFoundException("User not found with emailId: "+emailId);
		}
		
	}

}
