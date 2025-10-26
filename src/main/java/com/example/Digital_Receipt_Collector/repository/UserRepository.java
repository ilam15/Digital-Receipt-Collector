package com.example.Digital_Receipt_Collector.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Digital_Receipt_Collector.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
} 