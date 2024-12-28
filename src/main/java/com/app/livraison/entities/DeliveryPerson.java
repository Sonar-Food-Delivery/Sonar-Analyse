package com.app.livraison.entities;

import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String phone;        // Champ pour le numéro de téléphone
    private String vehicleType;  // Champ pour le type de véhicule (transport)
    private String status = "AVAILABLE"; // e.g., Available, On Delivery

    @OneToMany(mappedBy = "deliveryPerson")
    private List<Delivery> deliveries;
}
