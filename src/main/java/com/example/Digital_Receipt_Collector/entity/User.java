package com.example.Digital_Receipt_Collector.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String contact_no;
    private LocalDateTime join_date; 
    
    @OneToMany(mappedBy = "User", cascade = CascadeType.ALL)
    private List<Receipt> Reciept;
}