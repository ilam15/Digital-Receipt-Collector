package com.example.Digital_Receipt_Collector.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Digital_Receipt_Collector.entity.ReceiptCategory;

public interface ReceiptCategoryRepository extends JpaRepository<ReceiptCategory, Long> {
}