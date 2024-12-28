package com.app.livraison.entities;


import lombok.*;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
