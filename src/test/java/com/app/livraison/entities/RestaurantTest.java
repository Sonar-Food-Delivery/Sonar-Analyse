package com.app.livraison.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("La Belle Pizza");
        restaurant.setAddress("123 Rue du Gourmet");
        restaurant.setPhoneNumber("0612345678");
        restaurant.setRating(4.5);
        restaurant.setLogoRestau("logo.png");
        restaurant.setImageRestau("restaurant.jpg");
    }

    @Test
    void testRestaurantGettersAndSetters() {
        assertEquals(1L, restaurant.getId());
        assertEquals("La Belle Pizza", restaurant.getName());
        assertEquals("123 Rue du Gourmet", restaurant.getAddress());
        assertEquals("0612345678", restaurant.getPhoneNumber());
        assertEquals(4.5, restaurant.getRating());
        assertEquals("logo.png", restaurant.getLogoRestau());
        assertEquals("restaurant.jpg", restaurant.getImageRestau());
    }

    @Test
    void testRestaurantWithMeals() {
        Meal meal1 = new Meal();
        meal1.setId(101L);
        meal1.setName("Pizza Margherita");
        meal1.setRestaurant(restaurant);

        Meal meal2 = new Meal();
        meal2.setId(102L);
        meal2.setName("Pâtes Carbonara");
        meal2.setRestaurant(restaurant);

        restaurant.setMeals(List.of(meal1, meal2));

        assertNotNull(restaurant.getMeals());
        assertEquals(2, restaurant.getMeals().size());
        assertEquals("Pizza Margherita", restaurant.getMeals().get(0).getName());
    }

    @Test
    void testRestaurantEquality() {
        Restaurant anotherRestaurant = new Restaurant();
        anotherRestaurant.setId(1L);
        anotherRestaurant.setName("La Belle Pizza");
        anotherRestaurant.setAddress("123 Rue du Gourmet");
        anotherRestaurant.setPhoneNumber("0612345678");
        anotherRestaurant.setRating(4.5);
        anotherRestaurant.setLogoRestau("logo.png");
        anotherRestaurant.setImageRestau("restaurant.jpg");

        assertEquals(restaurant, anotherRestaurant);
        assertEquals(restaurant.hashCode(), anotherRestaurant.hashCode());
    }
}
