package serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vicary.entity.Account;
import org.vicary.entity.TransactionEntity;
import org.vicary.repository.TransactionRepository;
import org.vicary.service.AccountService;
import org.vicary.service.TransactionService;
import org.vicary.service.dto.TransactionRequest;
import org.vicary.service.map.TransactionMapper;

import java.time.OffsetDateTime;

import static org.mockito.Mockito.*;

public class TransactionServiceTest {
    private TransactionService transactionService;
    private TransactionRepository repository;
    private AccountService accountService;
    private TransactionMapper mapper;

    @BeforeEach
    public void setup() {
        repository = mock(TransactionRepository.class);
        accountService = mock(AccountService.class);
        mapper = mock(TransactionMapper.class);
        transactionService = new TransactionService(repository, accountService, mapper);
    }

    @Test
    public void createTransaction_ExpectCreated_ValidTransactionRequest() {
        //given
        TransactionRequest transactionRequest = new TransactionRequest(500, 1L, 3L);
        Account accountFrom = new Account(1L, "PLN", 2000, null);
        Account accountTo = new Account(3L, "EUR", 1000, null);
        TransactionEntity transactionEntity = new TransactionEntity(null, 500, "PLN", OffsetDateTime.now(), accountFrom, accountTo);

        when(accountService.findById(1L)).thenReturn(accountFrom);
        when(accountService.findById(3L)).thenReturn(accountTo);
        when(mapper.map(transactionRequest, accountFrom, accountTo)).thenReturn(transactionEntity);

        //when
        transactionService.createTransaction(transactionRequest);

        //verify
        verify(repository).save(eq(transactionEntity));
    }
}
