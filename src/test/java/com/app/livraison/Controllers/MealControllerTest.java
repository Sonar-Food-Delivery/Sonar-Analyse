package com.app.livraison.Controllers;

import com.app.livraison.controller.MealController;
import com.app.livraison.entities.Meal;
import com.app.livraison.service.MealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MealControllerTest {

    @Mock
    private MealService mealService;

    @InjectMocks
    private MealController mealController;

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
        when(mealService.getAllMeals()).thenReturn(List.of(meal));

        List<Meal> meals = mealController.getAllMeals();

        assertNotNull(meals);
        assertEquals(1, meals.size());
        assertEquals("Pizza Margherita", meals.get(0).getName());
        verify(mealService, times(1)).getAllMeals();
    }

    @Test
    void testGetMealById_Success() {
        when(mealService.getMealById(1L)).thenReturn(meal);

        Meal foundMeal = mealController.getMealById(1L);

        assertNotNull(foundMeal);
        assertEquals("Pizza Margherita", foundMeal.getName());
        verify(mealService, times(1)).getMealById(1L);
    }

    @Test
    void testGetMealById_NotFound() {
        when(mealService.getMealById(1L)).thenReturn(null);

        Meal foundMeal = mealController.getMealById(1L);

        assertNull(foundMeal);
        verify(mealService, times(1)).getMealById(1L);
    }

    @Test
    void testCreateMeal() {
        when(mealService.saveMeal(any(Meal.class))).thenReturn(meal);

        Meal createdMeal = mealController.createMeal(meal);

        assertNotNull(createdMeal);
        assertEquals("Pizza Margherita", createdMeal.getName());
        verify(mealService, times(1)).saveMeal(any(Meal.class));
    }

    @Test
    void testDeleteMeal() {
        doNothing().when(mealService).deleteMeal(1L);

        assertDoesNotThrow(() -> mealController.deleteMeal(1L));
        verify(mealService, times(1)).deleteMeal(1L);
    }
}
