package com.example.userportal.config;

import java.net.InetAddress;

import javax.annotation.PostConstruct;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;





@Component
@Scope("singleton")
public class EsClient {
	
	private @Value("${esPort}") int esPort;

	private @Value("${esHost}") String esHost;

	private @Value("${esCluster}") String esCluster;
	
	private @Value("${x-pack.username}") String xpackUserName;
	
	private @Value("${x-pack.password}") String xpackPassword;

	private TransportClient client;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(EsClient.class);

	@PostConstruct
	private void initializeClient() {

		LOGGER.info("Creating 6.2.2 elasticsearch client connection...");
		Settings settings = Settings.builder()
				.put("cluster.name", esCluster)
				.put("xpack.security.user", xpackUserName.concat(":").concat(xpackPassword)).build();
		try {
			client = new PreBuiltXPackTransportClient(settings)
					.addTransportAddress(new TransportAddress(InetAddress
							.getByName(esHost), esPort));
		} catch (Exception e) {
			e.printStackTrace();
		}   
		   
	}
	
	public TransportClient getClient(){
		return this.client;
	}

	public void destory() {
		this.client.close();
	}
}
