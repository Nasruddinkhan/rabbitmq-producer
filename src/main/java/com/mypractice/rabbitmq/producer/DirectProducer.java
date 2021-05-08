package com.mypractice.rabbitmq.producer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mypractice.rabbitmq.dto.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DirectProducer {

	private final RabbitTemplate rabbitTemplate;
	private final ObjectMapper objMapper;
	static List<String> list = new ArrayList<>();
	static {
		list.add("permanent");
		list.add("contract");
	}
	Supplier<String> empType = () -> list.get(new Random().nextInt(list.size()));;

	@Autowired
	public DirectProducer(RabbitTemplate rabbitTemplate, ObjectMapper objMapper) {
		super();
		this.rabbitTemplate = rabbitTemplate;
		this.objMapper = objMapper;
	}

	public void sendMessages(User user) throws Exception {
		String jsonString = objMapper.writeValueAsString(user);
		log.info("empType {}", empType.get());
		rabbitTemplate.convertAndSend("exchange.emp.type", empType.get(), jsonString);
	}

}
