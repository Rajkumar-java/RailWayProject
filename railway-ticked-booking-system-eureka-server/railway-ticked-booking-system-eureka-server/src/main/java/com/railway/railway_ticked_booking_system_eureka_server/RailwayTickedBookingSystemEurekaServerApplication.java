package com.railway.railway_ticked_booking_system_eureka_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RailwayTickedBookingSystemEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RailwayTickedBookingSystemEurekaServerApplication.class, args);
	}

}
