package com.example.demo.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserInformation;

public interface UserJpaRepository extends JpaRepository<UserInformation, String>{

	
	public Optional<UserInformation> findByEmailAndPassword(String email,String password);
}
