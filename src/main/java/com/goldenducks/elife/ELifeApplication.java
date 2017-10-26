package com.goldenducks.elife;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ELifeApplication {

	private static final Logger logger = LoggerFactory.getLogger(ELifeApplication.class);
	public static final String ERECIPE_SERVICE = "http://ERECIPE";

	public static void main(String[] args) {
		logger.debug("Starting eLife Applcation");
		SpringApplication.run(ELifeApplication.class, args);
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