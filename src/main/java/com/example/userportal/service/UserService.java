package com.example.userportal.service;

import java.awt.List;

import com.example.userportal.domain.User;

public interface UserService {

	public User create(User user);
	public User delete(int id);
	public List findAll();
	public User findById(int id);
	public User update(User user);
}
