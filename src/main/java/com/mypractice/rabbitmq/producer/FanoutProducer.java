package com.mypractice.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mypractice.rabbitmq.dto.User;

@Component
public class FanoutProducer {

	private final RabbitTemplate rabbitTemplate;
	private final ObjectMapper objMapper;
	@Autowired
	public FanoutProducer(RabbitTemplate rabbitTemplate,ObjectMapper objMapper) {
		super();
		this.rabbitTemplate = rabbitTemplate;
		this.objMapper = objMapper;
	}

	public void sendMessages(User user) throws Exception {
		String jsonString = objMapper.writeValueAsString(user);
		rabbitTemplate.convertAndSend("exchange.hr", "", jsonString);
	}
	
}
