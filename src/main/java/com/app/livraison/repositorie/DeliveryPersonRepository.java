package com.app.livraison.repositorie;

import com.app.livraison.entities.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, Long> {
//    DeliveryPerson findFirstByStatus(String status); // Trouver le premier livreur avec le statut "AVAILABLE"
@Query(value = "SELECT * FROM delivery_person WHERE status = :status LIMIT 1", nativeQuery = true)
DeliveryPerson findFirstByStatus(@Param("status") String status);

DeliveryPerson findByEmail(String email);
}
