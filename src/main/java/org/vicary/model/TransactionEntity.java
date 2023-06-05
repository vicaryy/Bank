package org.vicary.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Table(name = "TRANSACTIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private Long id;

    @Column(name = "AMOUNT")
    private double amount;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "TRANSACTION_DATE")
    private OffsetDateTime date;

    @ManyToOne
    @JoinColumn(name = "FROM_ACCOUNT_ID")
    private Account accountFrom;

    @ManyToOne
    @JoinColumn(name = "TO_ACCOUNT_ID")
    private Account accountTo;
}
