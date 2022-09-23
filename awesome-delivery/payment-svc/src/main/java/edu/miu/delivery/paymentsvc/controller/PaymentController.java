package edu.miu.delivery.paymentsvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.delivery.paymentsvc.dto.PaymentRequest;

@RestController
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @RabbitListener(queues = "#{queue.name}", concurrency = "10")
    public Boolean receive(PaymentRequest request) {
        logger.info("Payment request processed![{}]", request);
        return true;
    }
}
