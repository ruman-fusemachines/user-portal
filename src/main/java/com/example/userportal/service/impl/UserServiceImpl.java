package com.example.userportal.service.impl;

import java.awt.List;



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
	public User create(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public User delete(int id) {
		User user = findById(id);
		if(user != null) {
			userRepository.delete(user);
		}
		return user;
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User findById(int id) {
		return userRepository.findOne(id);
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
