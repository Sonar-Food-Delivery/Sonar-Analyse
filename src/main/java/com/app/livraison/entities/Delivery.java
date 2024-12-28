package com.app.livraison.entities;


import lombok.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trackingId;
    private String currentLocation;
    private String estimatedDeliveryTime;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "delivery_person_id")
    @JsonBackReference 
    private DeliveryPerson deliveryPerson;
}
