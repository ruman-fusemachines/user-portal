package com.example.userportal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userportal.service.EsTestService;




@RestController
@RequestMapping("/es")
public class EsTestController {

	@Autowired
	EsTestService esTestService;
	
	private static Logger logger = LoggerFactory.getLogger(EsTestController.class);
	
	@GetMapping("/status")
	public String getEsStatus(){
		logger.info("------GETTING STATUS------");
		return this.esTestService.getConnectionStatus();
	}
}
