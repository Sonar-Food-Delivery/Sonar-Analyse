package com.app.livraison.services;


import com.app.livraison.entities.Meal;
import com.app.livraison.repositorie.MealRepository;
import com.app.livraison.service.MealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MealServiceTest {

    @Mock
    private MealRepository mealRepository;

    @InjectMocks
    private MealService mealService;

    private Meal meal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        meal = new Meal();
        meal.setId(1L);
        meal.setName("Pizza Margherita");
    }

    @Test
    void testGetAllMeals() {
        when(mealRepository.findAll()).thenReturn(List.of(meal));

        List<Meal> meals = mealService.getAllMeals();

        assertNotNull(meals);
        assertEquals(1, meals.size());
        verify(mealRepository, times(1)).findAll();
    }

    @Test
    void testGetMealById_Success() {
        when(mealRepository.findById(1L)).thenReturn(Optional.of(meal));

        Meal foundMeal = mealService.getMealById(1L);

        assertNotNull(foundMeal);
        assertEquals("Pizza Margherita", foundMeal.getName());
        verify(mealRepository, times(1)).findById(1L);
    }

    @Test
    void testGetMealById_NotFound() {
        when(mealRepository.findById(1L)).thenReturn(Optional.empty());

        Meal foundMeal = mealService.getMealById(1L);

        assertNull(foundMeal);
        verify(mealRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveMeal() {
        when(mealRepository.save(meal)).thenReturn(meal);

        Meal savedMeal = mealService.saveMeal(meal);

        assertNotNull(savedMeal);
        assertEquals("Pizza Margherita", savedMeal.getName());
        verify(mealRepository, times(1)).save(meal);
    }

    @Test
    void testDeleteMeal() {
        doNothing().when(mealRepository).deleteById(1L);

        assertDoesNotThrow(() -> mealService.deleteMeal(1L));
        verify(mealRepository, times(1)).deleteById(1L);
    }
}
