package org.vicary.service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponse {

    private Long account_id;

    private String currency;

    private double amount;

    private Long user_id;

    public AccountResponse(String currency, double amount, Long user_id) {
        this.currency = currency;
        this.amount = amount;
        this.user_id = user_id;
    }
}
