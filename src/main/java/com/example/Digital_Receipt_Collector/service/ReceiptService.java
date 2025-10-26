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

    public List<Receipt> getAllReceipts() {
        return repository.findAll();
    }

    public Receipt updateReceipt(Long id, Receipt r) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(r.getName());
                    existing.setDescription(r.getDescription());
                    existing.setAmount(r.getAmount());
                    existing.setDate(r.getDate());
                    existing.setUser(r.getUser());
                    existing.setCategory(r.getCategory());
                    return repository.save(existing);
                })
                .orElseGet(() -> {
                    r.setId(id);
                    return repository.save(r);
                });
    }

    public void deleteReceipt(Long id) {
        repository.deleteById(id);
    }
}
