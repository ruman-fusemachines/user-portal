package com.example.userportal.repository.impl;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.userportal.config.EsClient;
import com.example.userportal.repository.EsTestRepository;





@Component
public class EsTestRepositoryImpl implements EsTestRepository {

	
	//ES CODE HERE
	@Autowired
	EsClient esClient;
	
	private static Logger logger = LoggerFactory.getLogger(EsTestRepositoryImpl.class);
	
	@Override
	public String getConnectionStatus() {
		// TODO Auto-generated method stub
		Client client =	this.esClient.getClient();
		
//		ClusterHealthResponse response = client.admin().cluster().prepareHealth().execute().actionGet();
//       return "\n"+
//                "Cluster Health: " + response.getStatus()+ "\n" +
//            "getActivePrimaryShards: " +response.getActivePrimaryShards() + "\n" +
//            "getActiveShards: " + response.getActiveShards() + "\n" +
//            "getDelayedUnassignedShards: " + response.getDelayedUnassignedShards()+ "\n" +
//            "getInitializingShards: " + response.getInitializingShards()+ "\n" +
//            "getNumberOfDataNodes: " + response.getNumberOfDataNodes()+ "\n" +
//            "getNumberOfInFlightFetch: " + response.getNumberOfInFlightFetch()+ "\n" +
//            "getNumberOfNodes: " + response.getNumberOfNodes()+ "\n" +
//            "getNumberOfPendingTasks: " + response.getNumberOfPendingTasks()+ "\n" +
//            "getRelocatingShards:  " + response.getRelocatingShards()+ "\n" +
//            "getUnassignedShards: " + response.getUnassignedShards()+ "\n";
		
		final ClusterHealthResponse response = client
			    .admin()
			    .cluster()
			    .prepareHealth()
			    .setWaitForGreenStatus()
			    .setTimeout( TimeValue.timeValueSeconds( 2 ) )
			    .execute()
			    .actionGet();
		

		if ( response.isTimedOut() ) {
			logger.info( "The cluster is unhealthy: " + response.getClusterName() + "-->" +response.getStatus() );
			return response.getClusterName().toString()+ "\t"+response.getStatus().toString();
		}

		logger.info( response.getClusterName() + "-->" +response.getStatus() );
		return response.getClusterName().toString()+ "\t"+response.getStatus().toString();
	}

}
