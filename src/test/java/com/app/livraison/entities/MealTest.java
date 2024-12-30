package com.app.livraison.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MealTest {

    private Meal meal;
    private Restaurant restaurant;
    private Category category;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Le Gourmet");

        category = new Category();
        category.setId(1L);
        category.setName("Italien");

        meal = new Meal();
        meal.setId(101L);
        meal.setName("Pizza Napoli");
        meal.setDescription("Délicieuse pizza napolitaine");
        meal.setPrice(12.99);
        meal.setImageUrl("pizza.jpg");
        meal.setAverageRating(4.7);
        meal.setIngredients(List.of("Tomate", "Mozzarella", "Basilic"));
        meal.setRestaurant(restaurant);
        meal.setCategory(category);
    }

    @Test
    void testMealGettersAndSetters() {
        assertEquals(101L, meal.getId());
        assertEquals("Pizza Napoli", meal.getName());
        assertEquals("Délicieuse pizza napolitaine", meal.getDescription());
        assertEquals(12.99, meal.getPrice());
        assertEquals("pizza.jpg", meal.getImageUrl());
        assertEquals(4.7, meal.getAverageRating());
        assertEquals(3, meal.getIngredients().size());
        assertEquals("Tomate", meal.getIngredients().get(0));
        assertEquals("Le Gourmet", meal.getRestaurant().getName());
        assertEquals("Italien", meal.getCategory().getName());
    }

    @Test
    void testMealWithOrders() {
        Order order = new Order();
        order.setId(501L);
        order.setStatus("Confirmed");

        meal.setOrders(List.of(order));

        assertNotNull(meal.getOrders());
        assertEquals(1, meal.getOrders().size());
        assertEquals(501L, meal.getOrders().get(0).getId());
    }

    @Test
    void testMealEqualityAndHashCode() {
        Meal anotherMeal = new Meal();
        anotherMeal.setId(101L);
        anotherMeal.setName("Pizza Napoli");
        anotherMeal.setDescription("Délicieuse pizza napolitaine");
        anotherMeal.setPrice(12.99);
        anotherMeal.setImageUrl("pizza.jpg");
        anotherMeal.setAverageRating(4.7);
        anotherMeal.setIngredients(List.of("Tomate", "Mozzarella", "Basilic"));
        anotherMeal.setRestaurant(restaurant);
        anotherMeal.setCategory(category);

        assertEquals(meal, anotherMeal);
        assertEquals(meal.hashCode(), anotherMeal.hashCode());
    }

    @Test
    void testMealNotEqual() {
        Meal differentMeal = new Meal();
        differentMeal.setId(102L);
        differentMeal.setName("Pasta Carbonara");

        assertNotEquals(meal, differentMeal);
    }

    @Test
    void testMealToString() {
        String mealString = meal.toString();
        assertTrue(mealString.contains("Pizza Napoli"));
        assertTrue(mealString.contains("12.99"));
        assertTrue(mealString.contains("Tomate"));
    }
}
