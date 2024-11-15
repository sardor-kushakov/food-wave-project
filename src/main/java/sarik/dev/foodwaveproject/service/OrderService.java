package sarik.dev.foodwaveproject.service;


import sarik.dev.foodwaveproject.dto.orderDto.OrderCreateDto;
import sarik.dev.foodwaveproject.dto.orderDto.OrderResponseDto;

import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderCreateDto orderCreateDto); // Order yaratish
    OrderResponseDto createOrderFromCart(Long cartId); // Savatchadan Order yaratish
    OrderResponseDto getOrderById(Long orderId); // ID orqali Order olish
    List<OrderResponseDto> getAllOrders(); // Hamma Orderlarni olish
    void updateOrderStatus(Long orderId, String newStatus); // Order statusini yangilash
    void deleteOrderById(Long orderId); // Orderni o'chirish
}