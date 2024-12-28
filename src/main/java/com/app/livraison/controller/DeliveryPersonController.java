package com.app.livraison.controller;

import com.app.livraison.entities.Delivery;
import com.app.livraison.entities.DeliveryPerson;
import com.app.livraison.service.DeliveryPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/delivery-persons")
public class DeliveryPersonController {
    @Autowired
    private DeliveryPersonService service;

    @GetMapping
    public List<DeliveryPerson> getAllDeliveryPersons() {
        return service.getAllDeliveryPersons();
    }

    @GetMapping("/{id}")
    public DeliveryPerson getDeliveryPersonById(@PathVariable Long id) {
        return service.getDeliveryPersonById(id);
    }
    
    @GetMapping("/{id}/orders")
    public List<Delivery> getOrdersForDeliveryPerson(@PathVariable Long id) {
        return service.getOrdersForDeliveryPerson(id);
    }

    
    @PostMapping
    public DeliveryPerson createDeliveryPerson(@RequestBody DeliveryPerson deliveryPerson) {
        // Assurez-vous que le mot de passe est bien traité et crypté si nécessaire
        return service.saveDeliveryPerson(deliveryPerson);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        // Vérifiez les informations d'identification
        DeliveryPerson deliveryPerson = service.getDeliveryPersonByEmail(email);

        if (deliveryPerson != null && deliveryPerson.getPassword().equals(password)) {
            // Connexion réussie
            return ResponseEntity.ok(Map.of(
                "message", "Login successful",
                "deliveryPersonId", deliveryPerson.getId(),
                "name", deliveryPerson.getName(),
                "email", deliveryPerson.getEmail()
            ));
        } else {
            // Échec de la connexion
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "error", "Invalid email or password"
            ));
        }
    }


    @DeleteMapping("/{id}")
    public void deleteDeliveryPerson(@PathVariable Long id) {
        service.deleteDeliveryPerson(id);
    }
}
