package com.app.livraison.services;

import com.app.livraison.entities.Restaurant;
import com.app.livraison.repositorie.RestaurantRepository;
import com.app.livraison.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantService restaurantService;

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
        when(restaurantRepository.findAll()).thenReturn(List.of(restaurant));

        List<Restaurant> result = restaurantService.getAllRestaurants();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(restaurantRepository, times(1)).findAll();
    }

    @Test
    void testGetRestaurantById_Success() {
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));

        Optional<Restaurant> found = restaurantService.getRestaurantById(1L);

        assertTrue(found.isPresent());
        assertEquals("La Belle Pizza", found.get().getName());
        verify(restaurantRepository, times(1)).findById(1L);
    }

    @Test
    void testGetRestaurantById_NotFound() {
        when(restaurantRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Restaurant> found = restaurantService.getRestaurantById(1L);

        assertFalse(found.isPresent());
        verify(restaurantRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveRestaurant() {
        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);

        Restaurant savedRestaurant = restaurantService.saveRestaurant(restaurant);

        assertNotNull(savedRestaurant);
        assertEquals("La Belle Pizza", savedRestaurant.getName());
        verify(restaurantRepository, times(1)).save(restaurant);
    }

    @Test
    void testDeleteRestaurant() {
        doNothing().when(restaurantRepository).deleteById(1L);

        assertDoesNotThrow(() -> restaurantService.deleteRestaurant(1L));
        verify(restaurantRepository, times(1)).deleteById(1L);
    }
}
