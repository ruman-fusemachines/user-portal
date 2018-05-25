package com.example.userportal.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userportal.domain.User;
import com.example.userportal.domain.dto.UserDto;
import com.example.userportal.service.UserService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping()
	public String create(@RequestBody UserDto userDto) {
		String msg =  userService.create(userDto.getUser());
		if(msg == "code") {
			return "created";
		}else {
			return "not created";
		}
	}
	
	@GetMapping(path = {"/{esId}"})
	public Map<String, Object> fineOne(@PathVariable("esId") String esId) {
		return userService.findById(esId);
	}
	
	@PutMapping("/{esId}")
	public Boolean update(@RequestBody User user, @PathVariable("esId") String esId) {
		return userService.update(user,  esId);
	}
	
	@DeleteMapping(path = {"/{esId}"})
	public Boolean delete(@PathVariable("esId") String esId) {
		return userService.delete(esId);
	}
	
	@GetMapping
	public List<Map<String, Object>> findAll() {
		return userService.findAll();
	}

}
