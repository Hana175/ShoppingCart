package com.example.shoppingcart.service.category;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.shoppingcart.model.Category;
import java.util.List;

public interface iCategoryService {
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category updateCategory(Category category,Long id);
    void deleteCategoryById(Long id);
}
