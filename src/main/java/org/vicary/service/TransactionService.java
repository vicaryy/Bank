package org.vicary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vicary.service.dto.TransactionRequest;
import org.vicary.model.Account;
import org.vicary.model.TransactionEntity;
import org.vicary.repository.TransactionRepository;
import org.vicary.service.map.TransactionMapper;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository repository;
    private final AccountService accountService;
    private final TransactionMapper mapper;

    public void createTransaction(TransactionRequest transactionRequest) {
        accountService.transferFunds(transactionRequest.getAccountIdFrom(), transactionRequest.getAccountIdTo(), transactionRequest.getAmount());

        Account accountFrom = accountService.findById(transactionRequest.getAccountIdFrom());
        Account accountTo = accountService.findById(transactionRequest.getAccountIdTo());
        TransactionEntity transactionEntity = mapper.map(transactionRequest, accountFrom, accountTo);

        repository.save(transactionEntity);
    }
}
