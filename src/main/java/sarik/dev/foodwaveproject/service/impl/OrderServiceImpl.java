package sarik.dev.foodwaveproject.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwaveproject.dto.orderDto.OrderCreateDto;
import sarik.dev.foodwaveproject.dto.orderDto.OrderResponseDto;
import sarik.dev.foodwaveproject.dto.orderItemDto.OrderItemDto;
import sarik.dev.foodwaveproject.entity.*;
import sarik.dev.foodwaveproject.entity.auth.AuthUser;
import sarik.dev.foodwaveproject.enums.OrderStatus;
import sarik.dev.foodwaveproject.repository.CartRepository;
import sarik.dev.foodwaveproject.repository.OrderRepository;
import sarik.dev.foodwaveproject.repository.PaymentRepository;
import sarik.dev.foodwaveproject.service.OrderService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final CartRepository cartRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            PaymentRepository paymentRepository,
                            CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.cartRepository = cartRepository;
    }

    @Transactional
    @Override
    public OrderResponseDto createOrder(OrderCreateDto orderCreateDto) {
//        // Foydalanuvchini olish (Session asosida ishlashni moslashtirish kerak)
//        Long sessionUserId = 1L;
//        AuthUser user = authUserRepository.findById(sessionUserId)
//                .orElseThrow(() -> new IllegalArgumentException("Foydalanuvchi topilmadi: ID = " + sessionUserId));
//
//        // To'lovni yaratish
//        Payment payment = new Payment();
//        payment.setPaymentMethod("Cash");
//        paymentRepository.save(payment);
//
//        // Buyurtmani yaratish
//        Order order = new Order();
//        order.setEmail(orderCreateDto.getEmail());
//        order.setUser(user);
//        order.setOrderDate(LocalDate.now());
//        order.setPayment(payment);
//        order.setOrderStatus(UserOrderStatus.PLACED.name());
//
//        // OrderItemlar yaratish
////        List<OrderItem> orderItems = orderCreateDto.getOrderItems().stream()
////                .map(dto -> toOrderItem(dto, order))
////                .collect(Collectors.toList());
////        order.setOrderItems(orderItems);
//        List<OrderItem> orderItems = orderCreateDto.getOrderItems().stream()
//                .map(dto -> toOrderItem(dto, order))
//                .collect(Collectors.toList());
//        order.setOrderItems(orderItems);
//
//
//        // Umumiy narxni hisoblash
//        double totalAmount = orderItems.stream()
//                .mapToDouble(item -> item.getOrderedProductPrice() * item.getQuantity())
//                .sum();
//        order.setTotalAmount(totalAmount);
//
//        // Orderni saqlash
//        orderRepository.save(order);
//        log.info("Buyurtma yaratildi: foydalanuvchi ID = {}", sessionUserId);
//
//        return toOrderResponseDto(order);
        return null;
    }

    @Transactional
    @Override
    public OrderResponseDto createOrderFromCart(Long cartId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Savatcha topilmadi: ID = " + cartId));

        AuthUser user = cart.getAuthUser();
        if (user == null) {
            throw new IllegalArgumentException("Foydalanuvchi ma'lumotlari mavjud emas");
        }

        Payment payment = new Payment();
        payment.setPaymentMethod("Cash");
        paymentRepository.save(payment);

        Order order = new Order();
        order.setEmail(user.getEmail());
        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setPayment(payment);
        order.setOrderStatus(OrderStatus.PLACED.name());

        List<OrderItem> orderItems = cart.getCartItems().stream()
                .map(cartItem -> toOrderItem(cartItem, order))
                .collect(Collectors.toList());
        order.setOrderItems(orderItems);

        double totalAmount = orderItems.stream()
                .mapToDouble(item -> item.getOrderedProductPrice() * item.getQuantity())
                .sum();
        order.setTotalAmount(totalAmount);

        orderRepository.save(order);

        // cart ni tozalash
//        cartRepository.deleteById(cartId);

        log.info("Savatchadan buyurtma yaratildi: foydalanuvchi ID = {}", user.getId());

        return toOrderResponseDto(order);
    }

    @Override
    public OrderResponseDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Buyurtma topilmadi: ID = " + orderId));
        return toOrderResponseDto(order);
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::toOrderResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void updateOrderStatus(Long orderId, String newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Buyurtma topilmadi: ID = " + orderId));

        String currentUserRole = getCurrentUserRole();

        validateStatusTransition(order.getOrderStatus(), newStatus, currentUserRole);

        order.setOrderStatus(newStatus);
        orderRepository.save(order);
    }

    private String getCurrentUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElseThrow(() -> new IllegalStateException("Ruxsatlar topilmadi"));
    }

    private void validateStatusTransition(String currentStatus, String newStatus, String currentUserRole) {
        switch (currentStatus) {
            case "PLACED":
                if (!newStatus.equals("CONFIRMED") || !currentUserRole.equals("ROLE_USER")) {
                    throw new IllegalStateException("Foydalanuvchi faqat PLACED holatidan CONFIRMED ga o'zgarishini tasdiqlashi mumkin.");
                }
                break;
            case "CONFIRMED":
            case "PREPARING":
            case "READY_FOR_PICKUP":
            case "OUT_FOR_DELIVERY":
                if (!currentUserRole.equals("ROLE_ADMIN")) {
                    throw new IllegalStateException("Bu holatlarni faqat admin o'zgartirishi mumkin.");
                }
                break;
            case "DELIVERED":
                if (!newStatus.equals("COMPLETED") || !currentUserRole.equals("ROLE_USER")) {
                    throw new IllegalStateException("Foydalanuvchi faqat DELIVERED holatidan COMPLETED ga o'zgarishini tasdiqlashi mumkin.");
                }
                break;
            default:
                throw new IllegalStateException("Noto'g'ri joriy status: " + currentStatus);
        }
    }

    @Override
    public void deleteOrderById(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new IllegalArgumentException("Buyurtma topilmadi: ID = " + orderId);
        }
        orderRepository.deleteById(orderId);
        log.info("Buyurtma o'chirildi: ID = {}", orderId);
    }


    private OrderItem toOrderItem(CartItem cartItem, Order order) {
        Product product = cartItem.getProduct();
        if (product == null) {
            throw new IllegalArgumentException("Mahsulot ma'lumotlari topilmadi: ID = " + cartItem.getProduct().getId());
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setDiscount(cartItem.getDiscount());
        orderItem.setOrderedProductPrice(cartItem.getProductPrice());
        return orderItem;
    }

    private OrderResponseDto toOrderResponseDto(Order order) {
        List<OrderItemDto> items = order.getOrderItems().stream()
                .map(item -> new OrderItemDto(
                        item.getProduct().getId(),
                        item.getProduct().getProductName(),
                        item.getQuantity(),
                        item.getDiscount(),
                        item.getOrderedProductPrice()))
                .collect(Collectors.toList());

        return new OrderResponseDto(
                order.getOrderId(),
                order.getEmail(),
                order.getOrderDate(),
                items,
                order.getTotalAmount(),
                OrderStatus.valueOf(order.getOrderStatus()));
    }

}
