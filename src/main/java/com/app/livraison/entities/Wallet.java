package com.app.livraison.entities;


import lombok.*;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double balance;
    @Temporal(TemporalType.DATE)
    private Date lastPaymentDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
