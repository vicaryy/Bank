package org.vicary.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    private double amount;
    private Long accountIdFrom;
    private Long accountIdTo;
}
