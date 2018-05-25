package com.example.userportal.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userportal.domain.User;
import com.example.userportal.repository.UserRepository;
import com.example.userportal.service.UserService;


@Service
public class UserServiceImpl implements UserService{


	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String create(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public Boolean delete(String esId) {
		Map<String, Object> user = findById(esId);
		if(user != null) {
			userRepository.delete(esId);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public List<Map<String, Object>> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public Map<String, Object> findById(String esId) {
		return userRepository.findOne(esId);
	}

	@Override
	public Boolean update(User user, String esId) {
		// TODO Auto-generated method stub
		return userRepository.update(user, esId);
	}

}
