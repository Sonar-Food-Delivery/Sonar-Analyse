package com.app.livraison.controller;

import com.app.livraison.entities.Restaurant;
import com.app.livraison.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {


        @Autowired
        private RestaurantService restaurantService;

        // Ajouter un restaurant
        @PostMapping
        public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
            return restaurantService.saveRestaurant(restaurant);
        }

        // Obtenir tous les restaurants
        @GetMapping
        public List<Restaurant> getAllRestaurants() {
            return restaurantService.getAllRestaurants();
        }

        // Obtenir un restaurant par ID
        @GetMapping("/{id}")
        public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
            return restaurantService.getRestaurantById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        // Mettre à jour un restaurant
        @PutMapping("/{id}")
        public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant) {
            try {
                Restaurant updatedRestaurant = restaurantService.updateRestaurant(id, restaurant);
                return ResponseEntity.ok(updatedRestaurant);
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }
        }

        // Supprimer un restaurant
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
            restaurantService.deleteRestaurant(id);
            return ResponseEntity.noContent().build();
        }

    }
