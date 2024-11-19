package sarik.dev.foodwaveproject.service.impl;

import org.springframework.stereotype.Service;
import sarik.dev.foodwaveproject.dto.order.history.OrderHistoryCreateDto;
import sarik.dev.foodwaveproject.dto.order.history.OrderHistoryDto;
import sarik.dev.foodwaveproject.entity.Order;
import sarik.dev.foodwaveproject.entity.OrderHistory;
import sarik.dev.foodwaveproject.enums.OrderStatus;
import sarik.dev.foodwaveproject.repository.OrderHistoryRepository;
import sarik.dev.foodwaveproject.repository.OrderRepository;
import sarik.dev.foodwaveproject.service.OrderHistoryService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

    private final OrderHistoryRepository orderHistoryRepository;
    private final OrderRepository orderRepository;
    private final OrderHistoryMapper orderHistoryMapper;

    public OrderHistoryServiceImpl(OrderHistoryRepository orderHistoryRepository,
                                   OrderRepository orderRepository,
                                   OrderHistoryMapper orderHistoryMapper) {
        this.orderHistoryRepository = orderHistoryRepository;
        this.orderRepository = orderRepository;
        this.orderHistoryMapper = orderHistoryMapper;
    }

    @Override
    public OrderHistoryDto create(OrderHistoryCreateDto createDto) {
        Order order = orderRepository.findById(createDto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + createDto.getOrderId()));

        OrderHistory orderHistory = orderHistoryMapper.toEntity(createDto);
        orderHistory.setOrder(order);

        orderHistory = orderHistoryRepository.save(orderHistory);
        return orderHistoryMapper.toDto(orderHistory);
    }

    @Override
    public List<OrderHistoryDto> getByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        List<OrderHistory> histories = orderHistoryRepository.findByOrder(order);
        return histories.stream()
                .map(orderHistoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderHistoryDto> getByStatus(OrderStatus status) {
        List<OrderHistory> histories = orderHistoryRepository.findByStatus(status);
        return histories.stream()
                .map(orderHistoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderHistoryDto> getByOrderIdAndStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        List<OrderHistory> histories = orderHistoryRepository.findByOrderAndStatus(order, status);
        return histories.stream()
                .map(orderHistoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderHistoryDto> getByChangeDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        List<OrderHistory> histories = orderHistoryRepository.findByChangeDateBetween(startDate, endDate);
        return histories.stream()
                .map(orderHistoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderHistoryDto getLatestByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        OrderHistory latestHistory = orderHistoryRepository.findTopByOrderOrderByChangeDateDesc(order);
        return orderHistoryMapper.toDto(latestHistory);
    }


    @Override
    public void delete(Long id) {
        if (!orderHistoryRepository.existsById(id)) {
            throw new RuntimeException("OrderHistory not found with ID: " + id);
        }
        orderHistoryRepository.deleteById(id);
    }
}
