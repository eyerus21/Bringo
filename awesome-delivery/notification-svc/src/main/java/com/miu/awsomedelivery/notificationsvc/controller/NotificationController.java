package com.miu.awsomedelivery.notificationsvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miu.awsomedelivery.notificationsvc.Enum.OrderStatus;
import com.miu.awsomedelivery.notificationsvc.model.Order;
import com.miu.awsomedelivery.notificationsvc.services.EmailService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
    @Autowired
    private EmailService emailService;
    private static final String subject = "Order Delivered!";
    @Autowired
    private ObjectMapper objectMapper;


    // Delivered status
    @KafkaListener(topics = "#{'${app.topic-name}'}", groupId = "#{'${spring.kafka.consumer.group-id}'}")
    public void listenOrder(ConsumerRecord<String, String> cr, @Payload String orderMessage) {
        logger.info("Received Order [key={} Payload={}]", cr.key(), orderMessage);
        try {
            Order order= objectMapper.readValue(orderMessage,Order.class);
            if(!order.getOrderStatus().equals(OrderStatus.DELIVERED)){
                return;
            }
            //send email to customer
            this.emailService.sendTextEmail(order
                            .getCustomer()
                            .getAddress()
                            .getEmailAddress()
                    ,subject
                    ,"Order delivered!");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
