package sarik.dev.foodwaveproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sarik.dev.foodwaveproject.entity.OrderHistory;
import sarik.dev.foodwaveproject.service.HistoryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/history")
public class HistoryController {
    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

//    @GetMapping
    List<OrderHistory> getAllHistory() {
        return historyService.getAllOrderHistory();
    }
}
