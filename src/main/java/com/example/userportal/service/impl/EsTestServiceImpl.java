package com.example.userportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userportal.repository.EsTestRepository;
import com.example.userportal.service.EsTestService;





@Service
public class EsTestServiceImpl implements EsTestService {
	
	@Autowired
	EsTestRepository esTestRepository;
	

	@Override
	public String getConnectionStatus() {
		
		return this.esTestRepository.getConnectionStatus();
	}

}
