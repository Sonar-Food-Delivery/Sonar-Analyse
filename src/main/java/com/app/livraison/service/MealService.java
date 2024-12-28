package com.app.livraison.service;

import com.app.livraison.entities.Meal;
import com.app.livraison.repositorie.MealRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {
    private final MealRepository mealRepository;

    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    public Meal getMealById(Long id) {
        return mealRepository.findById(id).orElse(null);
    }

    public Meal saveMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }

    // Recherche par nom
    public List<Meal> findMealsByName(String name) {
        return mealRepository.findByNameContainingIgnoreCase(name);
    }

    // Recherche par description
    public List<Meal> findMealsByDescription(String description) {
        return mealRepository.findByDescriptionContainingIgnoreCase(description);
    }
}
