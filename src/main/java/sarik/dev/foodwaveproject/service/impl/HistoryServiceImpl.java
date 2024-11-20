package sarik.dev.foodwaveproject.service.impl;

import org.springframework.stereotype.Service;
import sarik.dev.foodwaveproject.dto.productDto.ProductResponseDto;
import sarik.dev.foodwaveproject.entity.OrderHistory;
import sarik.dev.foodwaveproject.repository.OrderHistoryRepository;
import sarik.dev.foodwaveproject.repository.ProductRepository;
import sarik.dev.foodwaveproject.service.HistoryService;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class HistoryServiceImpl implements HistoryService {

    private final OrderHistoryRepository orderHistoryRepository;

    public HistoryServiceImpl(OrderHistoryRepository orderHistoryRepository) {
        this.orderHistoryRepository = orderHistoryRepository;
    }

    @Override
    public List<OrderHistory> getAllOrderHistory() {
        return orderHistoryRepository.findAll();
    }
//    public List<ProductResponseDto> getAllProducts() {
//        return productRepository.findAll()
//                .stream()
//                .map(productMapper::toProductResponseDto)
//                .collect(Collectors.toList());
//    }
}
