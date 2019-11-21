package com.spring.rabbitmq.receivers;

import java.util.concurrent.CountDownLatch;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.spring.rabbitmq.models.Message;

@Component
public class Receiver {
	private CountDownLatch latch = new CountDownLatch(1);

	//@RabbitListener(queues="spring-boot")
	public void receiveMessage(byte[] message) {
		String msg = new String(message);
		Message receivedMessage = new Gson().fromJson(msg, Message.class);
        System.out.println("Received a new notification...");
        System.out.println(receivedMessage.getId());
        System.out.println(receivedMessage.getMessage());
	}

	public CountDownLatch getLatch() {
		return latch;
	}
}
