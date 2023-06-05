package org.vicary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.vicary.service.dto.TransactionRequest;
import org.vicary.service.TransactionService;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService service;

    @PostMapping(path = "api/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@RequestBody TransactionRequest transactionRequest) {
        service.createTransaction(transactionRequest);
    }
}
