package sarik.dev.foodwaveproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwaveproject.dto.order.history.OrderHistoryCreateDto;
import sarik.dev.foodwaveproject.dto.order.history.OrderHistoryDto;
import sarik.dev.foodwaveproject.dto.order.history.OrderHistoryResponseDto;
import sarik.dev.foodwaveproject.entity.Order;
import sarik.dev.foodwaveproject.entity.OrderHistory;
import sarik.dev.foodwaveproject.exception.ResourceNotFoundException;
import sarik.dev.foodwaveproject.mapper.OrderHistoryMapper;
import sarik.dev.foodwaveproject.repository.OrderHistoryRepository;
import sarik.dev.foodwaveproject.repository.OrderRepository;
import sarik.dev.foodwaveproject.service.OrderHistoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderHistoryServiceImpl implements OrderHistoryService {

    private final OrderHistoryRepository orderHistoryRepository;
    private final OrderRepository orderRepository;
    private final OrderHistoryMapper orderHistoryMapper;

    @Override
    public OrderHistoryResponseDto create(Long orderId, OrderHistoryCreateDto createDto) {
        // Buyurtmani tekshirish
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Order not found with ID : '%d'", orderId)
                ));

        // Buyurtma tarixini yaratish
        OrderHistory orderHistory = orderHistoryMapper.fromCreateDto(createDto);
        orderHistory.setOrder(order);

        OrderHistory savedOrderHistory = orderHistoryRepository.save(orderHistory);
        return orderHistoryMapper.toResponseDto(savedOrderHistory);
    }

    @Override
    public OrderHistoryDto getById(Long id) {
        OrderHistory orderHistory = orderHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("OrderHistory not found with ID : '%d'", id)
                ));
        return orderHistoryMapper.toDto(orderHistory);
    }

    @Override
    public List<OrderHistoryResponseDto> getByOrderId(Long orderId) {
        List<OrderHistory> orderHistories = orderHistoryRepository.findByOrderId(orderId);
        if (orderHistories.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("No OrderHistory found for Order ID : '%d'", orderId)
            );
        }
        return orderHistories.stream()
                .map(orderHistoryMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderHistoryResponseDto> getAll() {
        List<OrderHistory> orderHistories = orderHistoryRepository.findAll();
        return orderHistories.stream()
                .map(orderHistoryMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        OrderHistory orderHistory = orderHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("OrderHistory not found with ID : '%d'", id)
                ));
        orderHistoryRepository.delete(orderHistory);
    }
}
