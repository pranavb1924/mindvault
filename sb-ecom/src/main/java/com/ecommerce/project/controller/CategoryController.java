package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class CategoryController {

    private List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;

    @GetMapping("/api/public/categories")
    public List<Category> getCategories() {
        return categories;
    }

    @PostMapping("/api/public/categories")
    public String createCategory(@RequestBody Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
        return "Category created";
    }

    @DeleteMapping("/api/admin/categories")
    public void deleteCategory(@RequestBody Long categoryId) {
        this.categories.remove(categoryId);
    }
}
