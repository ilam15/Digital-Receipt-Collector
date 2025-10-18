package com.example.Digital_Receipt_Collector.service;

import org.springframework.stereotype.Service;

import com.example.Digital_Receipt_Collector.entity.Receipt;

import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportsService {

    private final ReceiptService receiptService;

    public ReportsService(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    public Map<String, Object> monthlyReportForUser(Long userId, YearMonth month) {
        List<Receipt> receipts = receiptService.getAllReceiptsForUser(userId);
        double total = receipts.stream()
                .filter(r -> r.getDate() != null && YearMonth.from(r.getDate()).equals(month))
                .mapToDouble(Receipt::getAmount)
                .sum();

        Map<String, Object> result = new HashMap<>();
        result.put("userId", userId);
        result.put("month", month.toString());
        result.put("total", total);
        result.put("receipts", receipts.stream().filter(r -> r.getDate() != null && YearMonth.from(r.getDate()).equals(month)).collect(Collectors.toList()));
        return result;
    }

    public Map<String, Double> categoryWiseForUser(Long userId) {
        List<Receipt> receipts = receiptService.getAllReceiptsForUser(userId);
        return receipts.stream().filter(r -> r.getCategory() != null)
                .collect(Collectors.groupingBy(r -> r.getCategory().getName(), Collectors.summingDouble(Receipt::getAmount)));
    }
}
