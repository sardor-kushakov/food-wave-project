package sarik.dev.foodwaveproject.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sarik.dev.foodwaveproject.dto.orderDto.OrderCreateDto;
import sarik.dev.foodwaveproject.dto.orderDto.OrderResponseDto;
import sarik.dev.foodwaveproject.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        return ResponseEntity.ok(orderService.createOrder(orderCreateDto));
    }

    @PostMapping("/create-from-cart/{cartId}")
    public ResponseEntity<OrderResponseDto> createOrderFromCartId(@PathVariable Long cartId) {
        return ResponseEntity.ok(orderService.createOrderFromCart(cartId));
    }

    @GetMapping("/get-my-order")
    public ResponseEntity<List<OrderResponseDto>> getMyOrder() {
        return ResponseEntity.ok(orderService.getMyOrders());
    }

    @PostMapping("/create-from-many-carts/{cartIds}")
    public ResponseEntity<OrderResponseDto> createOrderFromManyCartIds(@PathVariable List<Long> cartIds) {
        return ResponseEntity.ok(orderService.createOrderFromMultipleCarts(cartIds));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/{orderId}/status")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long orderId,
                                                    @RequestParam String newStatus) {
        orderService.updateOrderStatus(orderId, newStatus);
        return ResponseEntity.ok("Buyurtma statusi yangilandi.");
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrderById(orderId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/soft-delete/{orderId}")
    public ResponseEntity<String> softDeleteOrder(@PathVariable Long orderId) {
        orderService.softDeleteOrderById(orderId);
        return ResponseEntity.ok("Buyurtma soft delete qilindi.");
    }
}
