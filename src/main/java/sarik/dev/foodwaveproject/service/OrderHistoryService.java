package sarik.dev.foodwaveproject.service;

import sarik.dev.foodwaveproject.dto.order.history.OrderHistoryCreateDto;
import sarik.dev.foodwaveproject.dto.order.history.OrderHistoryDto;
import sarik.dev.foodwaveproject.enums.OrderStatus;

import java.util.List;

public interface OrderHistoryService {

    // Yangi tarixiy yozuv yaratish
    OrderHistoryDto create(OrderHistoryCreateDto createDto);

    // Order ID orqali barcha tarixiy yozuvlarni olish
    List<OrderHistoryDto> getByOrderId(Long orderId);

    // Buyurtma holati bo'yicha tarixiy yozuvlarni olish
    List<OrderHistoryDto> getByStatus(OrderStatus status);

    // Tarixiy yozuvni ID bo'yicha o'chirish
    void delete(Long id);
}
