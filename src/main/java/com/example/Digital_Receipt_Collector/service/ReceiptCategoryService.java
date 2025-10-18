package com.example.Digital_Receipt_Collector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Digital_Receipt_Collector.entity.ReceiptCategory;
import com.example.Digital_Receipt_Collector.repository.ReceiptCategoryRepository;

import java.util.List;

@Service
public class ReceiptCategoryService {

    private final ReceiptCategoryRepository repository;

    @Autowired
    public ReceiptCategoryService(ReceiptCategoryRepository repository) {
        this.repository = repository;
    }

    public ReceiptCategory createCategory(ReceiptCategory category) {
        return repository.save(category);
    }

    public List<ReceiptCategory> getAllCategories() {
        return repository.findAll();
    }

    public java.util.Optional<ReceiptCategory> getById(Long id) {
        return repository.findById(id);
    }

    public ReceiptCategory updateCategory(Long id, ReceiptCategory c) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(c.getName());
                    existing.setDiscription(c.getDiscription());
                    return repository.save(existing);
                })
                .orElseGet(() -> {
                    c.setId(id);
                    return repository.save(c);
                });
    }

    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }
}

