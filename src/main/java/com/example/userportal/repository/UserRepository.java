package com.example.userportal.repository;

import java.awt.List;

import com.example.userportal.domain.User;

public interface UserRepository {

	 User save(User user);
	void delete(User user);
	 List findAll();
	 User findOne(int id);
	 User update(User user);
}
