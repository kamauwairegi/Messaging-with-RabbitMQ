package com.spring.rabbitmq.runner;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.spring.rabbitmq.SpringRabbitMqApplication;
import com.spring.rabbitmq.models.Message;
import com.spring.rabbitmq.receivers.Receiver;

@Component
public class Runner implements CommandLineRunner {
	private final RabbitTemplate rabbitTemplate;
	private final Receiver receiver;

	public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
		this.receiver = receiver;
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Sending message...");
		Message message = new Message();
		message.setId(1L);
		message.setMessage("Hello Springboot Messaging with RabbitMQ ");
		
		rabbitTemplate.convertAndSend(SpringRabbitMqApplication.topicExchangeName, "spring.boot.rabbitmq",
				message);
		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
	}
}
