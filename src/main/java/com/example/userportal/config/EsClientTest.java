//package com.example.userportal.config;
//
//import java.net.InetAddress;
//
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
//
//public class EsClientTest {
//
//public static void main(String[] args) {
//
//
//	
////	LOGGER.info("Creating 6.2.2 elasticsearch client connection...");
//	Settings settings = Settings.builder()
//			.put("cluster.name", "elasticsearch")
//			.put("xpack.security.user", "ruman".concat(":").concat("Room on1")).build();
//	try {
//		TransportClient client = new PreBuiltXPackTransportClient(settings)
//				.addTransportAddress(new TransportAddress(InetAddress
//						.getByName("localhost"), 9300));
//	} catch (Exception e) {
//		e.printStackTrace();
//	}   
//	   
//
//}
//	
//}

