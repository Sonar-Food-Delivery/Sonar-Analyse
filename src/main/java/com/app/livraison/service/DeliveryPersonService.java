package com.app.livraison.service;


import com.app.livraison.entities.Delivery;
import com.app.livraison.entities.DeliveryPerson;
import com.app.livraison.repositorie.DeliveryPersonRepository;
import com.app.livraison.repositorie.DeliveryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryPersonService {
    @Autowired
    private DeliveryPersonRepository repository;
    @Autowired
    private DeliveryRepository deliveryRepository;

    public List<DeliveryPerson> getAllDeliveryPersons() {
        return repository.findAll();
    }

    public DeliveryPerson getDeliveryPersonById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public DeliveryPerson saveDeliveryPerson(DeliveryPerson deliveryPerson) {
        return repository.save(deliveryPerson);
    }
    
    public DeliveryPerson getDeliveryPersonByEmail(String email) {
        return repository.findByEmail(email);
    }
    
    public List<Delivery> getOrdersForDeliveryPerson(Long deliveryPersonId) {
        return deliveryRepository.findByDeliveryPersonId(deliveryPersonId);
    }



    public void deleteDeliveryPerson(Long id) {
        repository.deleteById(id);
    }
}
