package com.app.livraison.repositorie;


import com.app.livraison.entities.Delivery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
	List<Delivery> findByDeliveryPersonId(Long deliveryPersonId);
}
