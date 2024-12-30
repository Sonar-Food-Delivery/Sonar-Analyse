package com.app.livraison.Controllers;

import com.app.livraison.controller.RestaurantController;
import com.app.livraison.entities.Restaurant;
import com.app.livraison.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantControllerTest {

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private RestaurantController restaurantController;

    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("La Belle Pizza");
    }

    @Test
    void testGetAllRestaurants() {
        when(restaurantService.getAllRestaurants()).thenReturn(List.of(restaurant));

        ResponseEntity<List<Restaurant>> response = restaurantController.getAllRestaurants();
        List<Restaurant> result = response.getBody();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("La Belle Pizza", result.get(0).getName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetRestaurantById_Success() {
        when(restaurantService.getRestaurantById(1L)).thenReturn(Optional.of(restaurant));

        ResponseEntity<Restaurant> response = restaurantController.getRestaurantById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("La Belle Pizza", response.getBody().getName());
    }

    @Test
    void testGetRestaurantById_NotFound() {
        when(restaurantService.getRestaurantById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Restaurant> response = restaurantController.getRestaurantById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testCreateRestaurant() {
        when(restaurantService.saveRestaurant(any(Restaurant.class))).thenReturn(restaurant);

        ResponseEntity<Restaurant> response = restaurantController.createRestaurant(restaurant);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("La Belle Pizza", response.getBody().getName());
    }
}
