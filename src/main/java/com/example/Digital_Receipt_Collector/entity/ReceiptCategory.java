package com.example.Digital_Receipt_Collector.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class ReceiptCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String discription;
    
    @OneToMany(mappedBy = "ReceiptCatogory", cascade = CascadeType.ALL)
    private List<Receipt> Reciept;
}