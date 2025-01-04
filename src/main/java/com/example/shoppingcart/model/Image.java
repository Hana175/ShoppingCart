package com.example.shoppingcart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity //this is an entity in the database
public class Image
{
    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;

    @Lob //for persistence.
    private Blob image;
    private String downloadUrl;
    //many to 1, many images belong to 1 product

    @ManyToOne
    @JoinColumn(name="product_id")//foreign key
    private Product product;
}
