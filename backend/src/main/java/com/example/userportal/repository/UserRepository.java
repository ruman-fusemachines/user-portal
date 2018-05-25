package com.example.userportal.repository;


import java.util.List;
import java.util.Map;

import com.example.userportal.domain.User;

public interface UserRepository {

	public String save(User user);
	public Boolean delete(String esId);
	public List<Map<String, Object>> findAll();
	public Map<String, Object> findOne(String esId);
	public Boolean update(User user, String esId);
}
