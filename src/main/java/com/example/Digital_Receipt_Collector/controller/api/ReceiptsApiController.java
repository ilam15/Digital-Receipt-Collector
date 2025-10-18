package com.example.Digital_Receipt_Collector.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Digital_Receipt_Collector.entity.Receipt;
import com.example.Digital_Receipt_Collector.service.ReceiptService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptsApiController {

    private final ReceiptService service;

    @Autowired
    public ReceiptsApiController(ReceiptService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Receipt> upload(@RequestBody Receipt r) {
        Receipt created = service.createReceipt(r);
        return ResponseEntity.created(URI.create("/api/receipts/" + created.getId())).body(created);
    }

    @GetMapping("/user/{id}")
    public List<Receipt> byUser(@PathVariable Long id) {
        return service.getAllReceiptsForUser(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receipt> byId(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{id}")
    public List<Receipt> byCategory(@PathVariable Long id) {
        return service.getByCategory(id);
    }
}
