package com.app.livraison.controller;


import com.app.livraison.entities.Meal;
import com.app.livraison.service.MealService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meals")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;

    @GetMapping
    public List<Meal> getAllMeals() {
        return mealService.getAllMeals();
    }

    @GetMapping("/{id}")
    public Meal getMealById(@PathVariable Long id) {
        return mealService.getMealById(id);
    }

    @PostMapping
    public Meal createMeal(@RequestBody Meal meal) {
        return mealService.saveMeal(meal);
    }

    @DeleteMapping("/{id}")
    public void deleteMeal(@PathVariable Long id) {
        mealService.deleteMeal(id);
    }
    @GetMapping("/searchByName")
    public List<Meal> searchMealsByName(@RequestParam String name) {
        return mealService.findMealsByName(name);
    }

    // Recherche par description
    @GetMapping("/searchByDescription")
    public List<Meal> searchMealsByDescription(@RequestParam String description) {
        return mealService.findMealsByDescription(description);
    }




}

