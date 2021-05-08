package com.mypractice.rabbitmq.config;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

@Configuration
public class RabbitmqConfig {

	public ObjectMapper objectMapper() {
		return JsonMapper.builder().findAndAddModules().build();
	}
	
}
