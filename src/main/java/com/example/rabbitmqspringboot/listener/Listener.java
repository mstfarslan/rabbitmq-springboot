package com.example.rabbitmqspringboot.listener;

import com.example.rabbitmqspringboot.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Listener {
    @RabbitListener(queues = "rabbitmq-queue" )

    public void handleMessage(Notification notification) {
        System.out.println("Message received..");
        System.out.println(notification.toString());
    }
}