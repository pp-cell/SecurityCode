package com.store.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "product_table"
)
public class Product {
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String p_name;
    private String category;
    private double price;

}
