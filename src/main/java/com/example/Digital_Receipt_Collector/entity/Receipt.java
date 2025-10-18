package com.example.Digital_Receipt_Collector.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Receipt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String discription;
}