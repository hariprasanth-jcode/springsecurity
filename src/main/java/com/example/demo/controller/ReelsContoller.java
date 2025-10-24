package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReelsContoller {

	@GetMapping("/reels")
	public String getReels() {
		return "Reels Uploaded Successfully";
	}
}
