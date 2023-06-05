package org.vicary.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionRequest {
    private double amount;
    private Long accountIdFrom;
    private Long accountIdTo;
}
