package com.example.rabbitmqspringboot.handler;

import com.example.rabbitmqspringboot.model.Notification;
import com.example.rabbitmqspringboot.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.UUID;

@Component
public class Sender {
    @Autowired
    private Producer producer;

    public void getThread() {
        Thread thread = new Thread(() -> {
            while (true) {
                Notification notification = new Notification();
                notification.setNotificationId(UUID.randomUUID().toString());
                notification.setCreatedAt(new Date());
                notification.setMessage(" message ...");
                notification.setSeen(false);
                producer.sendToQueue(notification);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setName("Notification sender");
        thread.start();
    }


    @PostConstruct
    public void init() {
        getThread();

    }
}
