package com.railway.railway_dashboard_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
public class RailwayDashboardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RailwayDashboardServiceApplication.class, args);
	}

}
