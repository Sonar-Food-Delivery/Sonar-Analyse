package com.app.livraison.service;

import com.app.livraison.entities.Restaurant;
import com.app.livraison.repositorie.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RestaurantService {




        @Autowired
        private RestaurantRepository restaurantRepository;

        // Ajouter un restaurant
        public Restaurant saveRestaurant(Restaurant restaurant) {
            return restaurantRepository.save(restaurant);
        }

        // Obtenir tous les restaurants
        public List<Restaurant> getAllRestaurants() {
            return restaurantRepository.findAll();
        }

        // Obtenir un restaurant par ID
        public Optional<Restaurant> getRestaurantById(Long id) {
            return restaurantRepository.findById(id);
        }
//        public Optional<Restaurant> getRestaurantById(Long id) {
//            return restaurantRepository.findById(id)
//                    .map(restaurant -> {
//                        restaurant.getMeals().size(); // Forcer le chargement
//                        return restaurant;
//                    });
//        }

        // Mettre à jour un restaurant
        public Restaurant updateRestaurant(Long id, Restaurant updatedRestaurant) {
            return restaurantRepository.findById(id).map(restaurant -> {
                restaurant.setName(updatedRestaurant.getName());
                restaurant.setAddress(updatedRestaurant.getAddress());
                restaurant.setPhoneNumber(updatedRestaurant.getPhoneNumber());
                restaurant.setRating(updatedRestaurant.getRating());
                return restaurantRepository.save(restaurant);
            }).orElseThrow(() -> new RuntimeException("Restaurant non trouvé"));
        }

        // Supprimer un restaurant
        public void deleteRestaurant(Long id) {
            restaurantRepository.deleteById(id);
        }
}
