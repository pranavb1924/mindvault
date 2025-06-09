package com.ecommerce.project.service;
import com.ecommerce.project.model.Category;
import java.util.*;

public interface CategoryService {
    List<Category> getAllCategories();
    void createCategory(Category category);
}
