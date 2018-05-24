package com.example.userportal.config;

import javax.annotation.PostConstruct;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
public class AppInitialization {
	@Autowired
	EsClient esClient;
	
	private @Value("${userIndex}") String userIndex;
	private @Value("${userType}") String userType;
	
	private Logger logger = LoggerFactory.getLogger(AppInitialization.class);
	
	@PostConstruct
	public void createIndexIfNoExist(){
		logger.info("Checking if User Index Exists!");
		if(!this.isIndexRegistered(userIndex)){
			this.createIndex(userIndex, "5", "0");
		}
	}
	
	public boolean isIndexRegistered( String indexName ) {
		// check if index already exists
		final IndicesExistsResponse ieResponse = this.esClient.getClient()
			    .admin()
			    .indices()
			    .prepareExists( indexName )
			    .get( TimeValue.timeValueSeconds( 1 ) );
			
		// index not there
		if ( !ieResponse.isExists() ) {
			return false;
		}
		
		logger.info( "Index already created!" );
		return true;
	}
	
	public boolean createIndex( String indexName, String numberOfShards, String numberOfReplicas ) {
		CreateIndexResponse createIndexResponse = 
			this.esClient.getClient().admin().indices().prepareCreate( indexName )
        	.setSettings( Settings.builder()             
                .put("index.number_of_shards", numberOfShards ) 
                .put("index.number_of_replicas", numberOfReplicas )
        	)
        	.get(); 
				
		if( createIndexResponse.isAcknowledged() ) {
			logger.info("Created Index with " + numberOfShards + " Shard(s) and " + numberOfReplicas + " Replica(s)!");
			return true;
		}
		
		return false;				
	}

}
