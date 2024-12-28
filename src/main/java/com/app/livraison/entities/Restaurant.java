package com.app.livraison.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String address;
        private String phoneNumber;
        private Double rating;
        private String LogoRestau;
        private String ImageRestau;

        // Relation 1:N avec Meal (Un restaurant a plusieurs repas)
        @OneToMany(mappedBy = "restaurant")
        private List<Meal> meals;
    }




