package edu.miu.delivery.ordersvc.dto;

import edu.miu.delivery.ordersvc.domain.Address;
import edu.miu.delivery.ordersvc.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private String id;
    private Address address;

    public static CustomerDTO create(Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getAddress());
    }
}
