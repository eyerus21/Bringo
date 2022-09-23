package com.miu.awsomedelivery.driversvc.model;

import com.miu.awsomedelivery.driversvc.Enum.OrderStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
public class Order {
    @Id
    String ID;
    private LocalDate date;
    private Double totalPrice;
    Customer customer;
    Driver driver;
    Restaurant restaurant;
    OrderStatus orderStatus;
}
