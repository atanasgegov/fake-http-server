package com.akg.fakehttpserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "common")
@Data
@EnableAsync
public class Config {

	private int numberOfExecutions;
	private String notificatorUrl;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
