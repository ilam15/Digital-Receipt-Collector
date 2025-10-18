package com.example.Digital_Receipt_Collector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Digital_Receipt_Collector.entity.Receipt;
import com.example.Digital_Receipt_Collector.repository.ReceiptRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiptService {

    private final ReceiptRepository repository;

    @Autowired
    public ReceiptService(ReceiptRepository repository) {
        this.repository = repository;
    }

    public Receipt createReceipt(Receipt r) {
        return repository.save(r);
    }

    public List<Receipt> getAllReceiptsForUser(Long userId) {
        // simple approach: findAll and filter (small dataset expected)
        return repository.findAll().stream().filter(r -> r.getUser() != null && r.getUser().getId().equals(userId)).toList();
    }

    public Optional<Receipt> getById(Long id) {
        return repository.findById(id);
    }

    public List<Receipt> getByCategory(Long categoryId) {
        return repository.findAll().stream().filter(r -> r.getCategory() != null && r.getCategory().getId().equals(categoryId)).toList();
    }
}
