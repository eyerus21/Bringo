package edu.miu.delivery.ordersvc.dto;

import java.time.LocalDateTime;
import java.util.Collection;

import edu.miu.delivery.ordersvc.domain.Menu;
import edu.miu.delivery.ordersvc.domain.Order;
import edu.miu.delivery.ordersvc.domain.Order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderMessage {
    private String orderId;
    private LocalDateTime orderDate;
    private CustomerDTO customer;
    private RestaurantDTO restaurant;
    private Object driver;
    private Collection<Menu> menus;
    private Double totalPrice;
    private OrderStatus status;

    public static OrderMessage create(Order order) {
        OrderMessage msg = new OrderMessage();
        msg.setOrderId(order.getId());
        msg.setOrderDate(order.getOrderDate());
        msg.setCustomer(CustomerDTO.create(order.getCustomer()));
        msg.setRestaurant(new RestaurantDTO(order.getRestaurantId()));
        msg.setMenus(order.getMenus());
        msg.setStatus(order.getStatus());
        msg.setTotalPrice(order.totalPrice());
        return msg;
    }
}
