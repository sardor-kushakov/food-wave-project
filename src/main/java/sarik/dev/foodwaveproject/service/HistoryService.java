package sarik.dev.foodwaveproject.service;

import sarik.dev.foodwaveproject.entity.OrderHistory;

import java.util.List;

public interface HistoryService {
    List<OrderHistory> getAllOrderHistory();
}
