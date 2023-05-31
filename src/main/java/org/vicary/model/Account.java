package org.vicary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_id;

    private String currency;

    private double amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Account(String currency, double amount, User user) {
        this.currency = currency;
        this.amount = amount;
        this.user = user;
    }
}
