package com.telusko.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    private String brand;

    @Column(nullable = false)
    private BigDecimal price;

    private String category;

    private Date releaseDate;
    private boolean productAvailable;
    private int stockQuantity;


    //product image details
    private String imageName;
    private String imageType;

    @Lob
    private byte[] imageData;

}
