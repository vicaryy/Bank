package org.vicary.service.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vicary.DataTime;
import org.vicary.service.dto.TransactionRequest;
import org.vicary.entity.Account;
import org.vicary.entity.TransactionEntity;

@Component
public class TransactionMapper {
    DataTime offsetDateTime;
    @Autowired
    public TransactionMapper(DataTime offsetDateTime) {
        this.offsetDateTime = offsetDateTime;
    }
    public TransactionEntity map(TransactionRequest transactionRequest, Account accountFrom, Account accountTo) {
        return TransactionEntity.builder()
                .amount(transactionRequest.getAmount())
                .currency(accountFrom.getCurrency())
                .date(offsetDateTime.now())
                .accountFrom(accountFrom)
                .accountTo(accountTo)
                .build();
    }
}
