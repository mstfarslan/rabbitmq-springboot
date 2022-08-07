package com.example.rabbitmqspringboot.producer;

import com.example.rabbitmqspringboot.model.Notification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Value("${sr.rabbit.routing.name}")
    private String routingName;

    @Value("${sr.rabbit.exchange.name}")
    private String exchangeName;


    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendToQueue(Notification notification) {
        System.out.println("Notification Sent ID : " + notification.getNotificationId());
        rabbitTemplate.convertAndSend(exchangeName, routingName, notification);
    }
}
