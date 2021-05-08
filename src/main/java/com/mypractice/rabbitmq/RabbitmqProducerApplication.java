package com.mypractice.rabbitmq;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mypractice.rabbitmq.dto.User;
import com.mypractice.rabbitmq.producer.TopicProducer;

@SpringBootApplication
public class RabbitmqProducerApplication implements CommandLineRunner {
	@Autowired
	TopicProducer topicProducer;

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		// firstProducer.sendHelloMsg("Nasruddin your first msg with id
		// ["+Math.random()+"]ō");
		// Arrays.asList(User)

		for (int i = 0; i < 100; i++) {
			User user = User.builder().userId(UUID.randomUUID().toString()).dob(LocalDate.now()).firstName("Nasruddin")
					.lastName("khan").emailId("nasruddinkhan44gmail.com").phoneNo("9594757518")
					.docSize(ThreadLocalRandom.current().nextInt(1, 10000))
					.build();
			topicProducer.sendMessages(user);
		}
	}

}
