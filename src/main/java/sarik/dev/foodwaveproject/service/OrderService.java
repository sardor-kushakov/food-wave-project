package sarik.dev.foodwaveproject.service;


import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderCreateDto orderCreateDto); // Order yaratish
    OrderResponseDto createOrderFromCart(Long cartId); // Savatchadan Order yaratish
    OrderResponseDto getOrderById(Long orderId); // ID orqali Order olish
    List<OrderResponseDto> getAllOrders(); // Hamma Orderlarni olish
    void updateOrderStatus(Long orderId, String newStatus); // Order statusini yangilash
    void deleteOrderById(Long orderId); // Orderni o'chirish
}