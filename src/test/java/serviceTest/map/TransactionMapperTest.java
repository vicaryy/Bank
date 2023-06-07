package serviceTest.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vicary.DataTime;
import org.vicary.entity.Account;
import org.vicary.entity.TransactionEntity;
import org.vicary.service.dto.TransactionRequest;
import org.vicary.service.map.TransactionMapper;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionMapperTest {
    private TransactionMapper mapper;
    private DataTime offsetDateTime;

    @BeforeEach
    public void setup() {
        offsetDateTime = mock(DataTime.class);
        mapper = new TransactionMapper(offsetDateTime);
    }

    @Test
    public void mapTransactionRequestToTransactionEntity_ExpectEquals_ValidRequest() {
        //given
        TransactionRequest transactionRequest = new TransactionRequest(500, 1L, 3L);
        OffsetDateTime date = OffsetDateTime.now();
        Account accountFrom = new Account(1L, "PLN", 1000, null);
        Account accountTo = new Account(3L, "EUR", 2000, null);
        TransactionEntity transactionEntityExpected = new TransactionEntity(null, 500, "PLN", date, accountFrom, accountTo);
        when(offsetDateTime.now()).thenReturn(date);

        //verify
        assertEquals(transactionEntityExpected, mapper.map(transactionRequest, accountFrom, accountTo));
    }

    @Test
    public void mapTransactionRequestToTransactionEntity_ExpectNotEquals_ValidRequest() {
        //given
        TransactionRequest transactionRequest = new TransactionRequest(500, 1L, 3L);
        OffsetDateTime date = OffsetDateTime.now();
        Account accountFrom = new Account(1L, "PLN", 1000, null);
        Account accountTo = new Account(3L, "EUR", 2000, null);
        TransactionEntity transactionEntityExpected = new TransactionEntity(null, 501, "PLN", date, accountFrom, accountTo);
        when(offsetDateTime.now()).thenReturn(date);

        //verify
        assertNotEquals(transactionEntityExpected, mapper.map(transactionRequest, accountFrom, accountTo));
    }
}
