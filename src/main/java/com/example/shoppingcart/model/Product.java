package com.example.shoppingcart.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Data
//@AllArgsConstructor
@NoArgsConstructor
@Entity //this class will represent a table in the database
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;

    //when a product is deleted, category will not depend on product and product does not depend on category
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")//foreign key
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval=true)//when a product is deleted, all associated images are going to be removed.
    private List<Image> images;

    public Product(String name, String brand, BigDecimal price, int inventory, String description, Category category) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.inventory = inventory;
        this.description = description;
        this.category = category;
    }
}
