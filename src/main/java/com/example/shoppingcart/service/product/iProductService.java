package com.example.shoppingcart.service.product;

import java.util.List;
import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.requests.AddProductRequest;
import com.example.shoppingcart.requests.ProductUpdateRequest;

//jpa will write all of these queries
public interface iProductService {
    //add product method to db
    Product addProduct(AddProductRequest request);
    Product getProductById(Long id);
    void deleteProduct(Long id);
    Product updateProduct(ProductUpdateRequest product, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);
    Long countProductsByBrandAndName(String brand, String name);

}
