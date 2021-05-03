package com.mypractice.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mypractice.rabbitmq.producer.HelloFirstProducer;

@SpringBootApplication
public class RabbitmqProducerApplication implements CommandLineRunner {
	@Autowired
	HelloFirstProducer firstProducer;
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		firstProducer.sendHelloMsg("Nasruddin your first msg with id ["+Math.random()+"]ō");
	}

}
