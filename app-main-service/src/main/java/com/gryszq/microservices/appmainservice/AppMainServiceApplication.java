package com.gryszq.microservices.appmainservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AppMainServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppMainServiceApplication.class, args);
	}

}
