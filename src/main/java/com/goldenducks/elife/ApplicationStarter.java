package com.goldenducks.elife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.goldenducks.elife.microservices.ERecipeService;
import com.goldenducks.elife.microservices.RemoteERecipeService;

@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationStarter {

	public static final String ERECIPE_SERVICE = "http://ERECIPE";

	public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ERecipeService accountRepository() {
		return new RemoteERecipeService(ERECIPE_SERVICE);
	}
}