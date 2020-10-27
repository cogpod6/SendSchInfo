package com.cts.learning.SendSchInfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SendSchInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendSchInfoApplication.class, args);
	}

}
