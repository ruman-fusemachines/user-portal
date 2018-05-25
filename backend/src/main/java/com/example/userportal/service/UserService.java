package com.example.userportal.service;




import java.util.List;
import java.util.Map;

import com.example.userportal.domain.User;

public interface UserService {

	public String create(User user);
	public Boolean delete(String esId);
	public List<Map<String, Object>> findAll();
	public Map<String, Object> findById(String esId);
	public Boolean update(User user, String esId);
}
