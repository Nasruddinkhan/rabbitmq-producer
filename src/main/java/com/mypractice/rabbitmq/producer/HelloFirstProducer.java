package com.mypractice.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class HelloFirstProducer {

	private int i = 0;
	private final RabbitTemplate rabbitTemplate;
	@Autowired
	public HelloFirstProducer(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendHelloMsg(String message) {
		rabbitTemplate.convertAndSend("firstapp.queue", "Welcome "+message);
	}
	@Scheduled(fixedRate = 30000)
	public void sendMessage() {
		i++;
		System.out.println("fixedrate.queue FixedRate : [ "+i+" ]");
		rabbitTemplate.convertAndSend("fixedrate.queue", "FixedRate : [ "+i+" ]");
	}
	
	
}
	