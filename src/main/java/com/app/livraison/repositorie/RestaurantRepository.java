package com.app.livraison.repositorie;

import com.app.livraison.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {


    }

