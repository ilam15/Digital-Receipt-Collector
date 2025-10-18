package com.example.Digital_Receipt_Collector.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Digital_Receipt_Collector.entity.ReceiptCategory;
import com.example.Digital_Receipt_Collector.service.ReceiptCategoryService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesApiController {

    private final ReceiptCategoryService service;

    @Autowired
    public CategoriesApiController(ReceiptCategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ReceiptCategory> create(@RequestBody ReceiptCategory c) {
        ReceiptCategory created = service.createCategory(c);
        return ResponseEntity.created(URI.create("/api/categories/" + created.getId())).body(created);
    }

    @GetMapping
    public List<ReceiptCategory> list() {
        return service.getAllCategories();
    }
}
