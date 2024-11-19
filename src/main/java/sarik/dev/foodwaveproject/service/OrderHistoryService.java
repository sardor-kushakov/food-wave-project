package sarik.dev.foodwaveproject.service;

import sarik.dev.foodwaveproject.dto.order.history.OrderHistoryCreateDto;
import sarik.dev.foodwaveproject.dto.order.history.OrderHistoryDto;
import sarik.dev.foodwaveproject.dto.order.history.OrderHistoryResponseDto;

import java.util.List;

public interface OrderHistoryService {

    // Yangi buyurtma tarixini yaratish
    OrderHistoryResponseDto create(Long orderId, OrderHistoryCreateDto createDto);

    // ID bo'yicha buyurtma tarixini olish
    OrderHistoryDto getById(Long id);

    // Buyurtma ID bo'yicha barcha tarixlarni olish
    List<OrderHistoryResponseDto> getByOrderId(Long orderId);

    // Barcha buyurtma tarixlarini olish
    List<OrderHistoryResponseDto> getAll();

    // Buyurtma tarixini o'chirish
    void delete(Long id);
}