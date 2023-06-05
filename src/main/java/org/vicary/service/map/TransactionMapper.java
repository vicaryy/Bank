package org.vicary.service.map;

import org.springframework.stereotype.Component;
import org.vicary.service.dto.TransactionRequest;
import org.vicary.model.Account;
import org.vicary.model.TransactionEntity;

import java.time.OffsetDateTime;

@Component
public class TransactionMapper {
    public TransactionEntity map(TransactionRequest transactionRequest, Account accountFrom, Account accountTo) {
        return TransactionEntity.builder()
                .amount(transactionRequest.getAmount())
                .currency(accountFrom.getCurrency())
                .date(OffsetDateTime.now())
                .accountFrom(accountFrom)
                .accountTo(accountTo)
                .build();
    }
}
