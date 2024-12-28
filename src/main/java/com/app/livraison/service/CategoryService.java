package com.app.livraison.service;
import com.app.livraison.entities.Category;
import com.app.livraison.entities.Meal;
import com.app.livraison.entities.Restaurant;
import com.app.livraison.repositorie.CategoryRepository;
import com.app.livraison.repositorie.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final MealRepository mealRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, MealRepository mealRepository) {
        this.categoryRepository = categoryRepository;
        this.mealRepository = mealRepository;
    }

    // Méthode pour ajouter une nouvelle catégorie et associer des repas existants
    public Category addCategoryWithMeals(Category category) {
        // Récupérer les repas associés par leurs ID et les associer à la catégorie
        List<Meal> meals = category.getMeals();
        for (Meal meal : meals) {
            Optional<Meal> existingMeal = mealRepository.findById(meal.getId());
            existingMeal.ifPresent(m -> m.setCategory(category));  // Associer le repas à la catégorie
        }

        // Sauvegarder la catégorie (avec les repas associés)
        return categoryRepository.save(category);
    }

    // Optionnel : Méthode pour récupérer une catégorie par son ID
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Optionnel : Méthode pour récupérer toutes les catégories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Restaurant> getRestaurantsByCategoryId(Long categoryId) {
        return categoryRepository.findRestaurantsByCategoryId(categoryId);
    }
    public List<Category> getCategoriesWithMeals() {
        return categoryRepository.findCategoriesWithMeals();
    }


}
