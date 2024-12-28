package com.app.livraison.controller;
import com.app.livraison.entities.Category;
import com.app.livraison.entities.Meal;
import com.app.livraison.entities.Restaurant;
import com.app.livraison.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Méthode pour créer une catégorie et associer des repas existants

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.addCategoryWithMeals(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    // Optionnel : Méthode pour récupérer une catégorie par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Optionnel : Méthode pour récupérer toutes les catégories
    @GetMapping
    public ResponseEntity<Iterable<Category>> getAllCategories() {
        Iterable<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @GetMapping("/{id}/restaurants")
    public ResponseEntity<List<Restaurant>> getRestaurantsByCategory(@PathVariable Long id) {
        List<Restaurant> restaurants = categoryService.getRestaurantsByCategoryId(id);
        return ResponseEntity.ok(restaurants);
    }
    @GetMapping("/with-meals")
    public ResponseEntity<List<Category>> getCategoriesWithMeals() {
        List<Category> categories = categoryService.getCategoriesWithMeals();
        return ResponseEntity.ok(categories);
    }

}
