package com.example.userportal.repository.impl;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ExecutionException;



import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.userportal.config.EsClient;
import com.example.userportal.domain.User;
import com.example.userportal.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class UserRepositoryImpl implements UserRepository{
	
	private @Value("${employeeIndex}") String employeeIndex;
	private @Value("${employeeType}") String employeeType;
	
	@Autowired
	private EsClient esClient;
	
	private static Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

	@Override
	public String save(User user) {
		String esId = UUID.randomUUID().toString();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonCv=null;
		try {
			jsonCv = objectMapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		IndexResponse ir = this.esClient.getClient().prepareIndex(employeeIndex, employeeType)
				.setSource(jsonCv, XContentType.JSON).get();
		
		// Document ID (generated or not)
					String _id = ir.getId();
					logger.info("_id: "+_id);
					
					// Version (if it's the first time you index this document, you will get: 1)
					long _version = ir.getVersion();
					logger.info("_version: "+_version);
		user.setEsId(esId);
		return ir.status().toString();
	}

	@Override
	public Boolean delete(String esId) {
		Map<String, Object> employee = findOne(esId);
		if (employee != null) {
		try {
			DeleteResponse deleteResponse = this.esClient.getClient().prepareDelete(employeeIndex, employeeType, esId).get();
			if(deleteResponse != null){
				logger.info("Employee Deleted : {}", deleteResponse);
				return true;
			}
			else{
				logger.info("Emplyoee not deleted : {}", deleteResponse);
				return false;
			}
			
		} catch (Exception e) {
			logger.info("Emplyoee not deleted : {}");
			return false;
		}
		}
		else {
			logger.info("Cannot find employee ");
			return false;
		}
		
	}

	@Override
	public List<Map<String, Object>> findAll() {
		SearchRequestBuilder srb = this.esClient.getClient().prepareSearch(employeeIndex).setTypes(employeeType);
		BoolQueryBuilder mainBoolQuery = new BoolQueryBuilder();
		BoolQueryBuilder subBoolQuery = new BoolQueryBuilder();
		subBoolQuery.should(QueryBuilders.matchAllQuery());
		mainBoolQuery.must(subBoolQuery);
		srb = srb.setQuery(mainBoolQuery);
		
		List<Map<String, Object>> users = new ArrayList<>();
		Map<String, Object> finalResult = new LinkedHashMap<String, Object>();
		SearchResponse responses = srb.get();
		
		for (SearchHit searchHit : responses.getHits().getHits()) {
			finalResult.put("id", searchHit.getId());
			finalResult.putAll(searchHit.getSourceAsMap());
			users.add(finalResult);
		}
		
		return users;
	}

	@Override
	public Map<String, Object> findOne(String esId) {
	
		Map<String, Object> finalResult = new TreeMap<>();
		GetRequestBuilder response = this.esClient.getClient().prepareGet(employeeIndex,employeeType, esId);
		GetResponse result = response.get();
		
		finalResult = result.getSourceAsMap();
		return finalResult;
	}

	@Override
	public Boolean update(User user,String esId) {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonCv=null;
		try {
			jsonCv = objectMapper.writeValueAsString(user);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		
		UpdateRequest updateRequest = new UpdateRequest(employeeIndex, employeeType, esId)
		    .doc(jsonCv, XContentType.JSON);
		try {
			UpdateResponse updateResponse = this.esClient
					.getClient()
					.update(updateRequest)
					.get();
			RestStatus status = updateResponse.status();
			logger.info("_status:"+ status);
			return true;
		} catch (InterruptedException e) {
			logger.warn("{}",e);
		} catch (ExecutionException e) {
			logger.warn("{}",e);
		}
		return false;
	}

	

}
