package sarik.dev.foodwaveproject.dto.order;


import lombok.AllArgsConstructor;
import lombok.Data;
import sarik.dev.foodwaveproject.dto.order.item.OrderItemDto;
import sarik.dev.foodwaveproject.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponseDto {
    private Long orderId;
    private String email;
    private LocalDate orderDate;
    private List<OrderItemDto> orderItems;
    private double totalAmount;
    private OrderStatus orderStatus;

}