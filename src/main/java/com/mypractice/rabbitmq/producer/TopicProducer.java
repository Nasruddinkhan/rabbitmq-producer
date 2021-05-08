package com.mypractice.rabbitmq.producer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mypractice.rabbitmq.dto.User;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TopicProducer {

	private final RabbitTemplate rabbitTemplate;
	private final ObjectMapper objMapper;

	static List<String> docList = new ArrayList<>();
	static {
		docList.add("png");
		docList.add("jpg");
		docList.add("svg");
		docList.add("pdf");
		docList.add("doc");
		docList.add("xlx");
		docList.add("xlxs");

	}
	Supplier<String> docTYpe = () -> docList.get(new Random().nextInt(docList.size()));


	@Autowired
	public TopicProducer(RabbitTemplate rabbitTemplate, ObjectMapper objMapper) {
		super();
		this.rabbitTemplate = rabbitTemplate;
		this.objMapper = objMapper;
	}

	public boolean routeKey(Predicate<String> function, String docType) {
		return function.test(docType);

	}

	public void sendMessages(User user) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("q").append(".").append("doctype").append(".");
		if (user.getDocSize() > 8000) {
			builder.append("large");
			user.setDocType("file to large");

		}
		else {
			String fileType = docTYpe.get();
			builder.append(fileType);
			user.setDocType(UUID.randomUUID().toString() + "." + fileType);
		}
		String jsonString = objMapper.writeValueAsString(user);
		log.info("route key {}", builder.toString());
		log.info("jsonString {}", jsonString);
		rabbitTemplate.convertAndSend("exchange.emp.docs", builder.toString(), jsonString);

	}

}
