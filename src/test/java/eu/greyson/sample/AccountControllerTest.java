package eu.greyson.sample;

import eu.greyson.sample.general.dto.AccountDto;
import eu.greyson.sample.general.dto.CardDto;
import eu.greyson.sample.general.dto.TransactionDto;
import eu.greyson.sample.general.rest.impl.AccountControllerImpl;
import eu.greyson.sample.general.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountControllerImpl.class)
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountService service;

    @Test
    void getUserAccountsTest() throws Exception{
        AccountDto account1 = new AccountDto();
        account1.setName("account1");
        account1.setBalance(12);
        List<AccountDto> accounts = List.of(account1);
        when(service.getAccountsByUserId(1L)).thenReturn(accounts);
        this.mockMvc.perform(get("/accounts?userId=1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(equalTo(account1.getName())))
                .andExpect(jsonPath("$[0].balance").value(equalTo(account1.getBalance())));
    }

    @Test
    void getAccountDetailTest() throws Exception {
        AccountDto account1 = new AccountDto();
        account1.setName("account1");
        account1.setBalance(1000);
        when(service.getAccount(1L)).thenReturn(account1);
        this.mockMvc.perform(get("/accounts/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(equalTo(account1.getName())))
                .andExpect(jsonPath("$.balance").value(equalTo(account1.getBalance())));
    }

    @Test
    void getAccountTransactionsTest() throws Exception {
        TransactionDto transaction = new TransactionDto();
        transaction.setDebtor("IBAN1");
        transaction.setCreditor("IBAN2");
        transaction.setDateCreated(LocalDateTime.now());
        transaction.setAmount(2002);

        when(service.getTransactionsWithAccountId(1L)).thenReturn(List.of(transaction));
        this.mockMvc.perform(get("/accounts/1/transactions")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].debtor").value(equalTo(transaction.getDebtor())))
                .andExpect(jsonPath("$[0].creditor").value(equalTo(transaction.getCreditor())))
                .andExpect(jsonPath("$[0].dateCreated").value(equalTo(transaction.getDateCreated().toString())))
                .andExpect(jsonPath("$[0].amount").value(equalTo(transaction.getAmount())));
    }

    @Test
    void postAccountTransactionTest() throws Exception {
        TransactionDto transaction = new TransactionDto();
        transaction.setDebtor("IBAN1");
        transaction.setCreditor("IBAN2");
        transaction.setDateCreated(LocalDateTime.now());
        transaction.setAmount(2002);
        when(service.addTransaction(1L, "IBAN1", 2002)).thenReturn(transaction);
        this.mockMvc.perform(
                post("/accounts/1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"debtor\": \"IBAN1\", \"amount\": 2002}")
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.debtor").value(equalTo(transaction.getDebtor())))
                .andExpect(jsonPath("$.creditor").value(equalTo(transaction.getCreditor())))
                .andExpect(jsonPath("$.dateCreated").value(equalTo(transaction.getDateCreated().toString())))
                .andExpect(jsonPath("$.amount").value(equalTo(transaction.getAmount())));
    }

    @Test
    void getAccountCardsTest() throws Exception {
        CardDto card = new CardDto();
        card.setBlocked(false);
        card.setName("card1");
        when(service.getAccountCards(1L)).thenReturn(List.of(card));
        this.mockMvc.perform(get("/accounts/1/cards")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(equalTo(card.getName())))
                .andExpect(jsonPath("$[0].blocked").value(equalTo(card.isBlocked())));
    }

    @Test
    void getAccountCardDetailTest() throws Exception {
        CardDto card = new CardDto();
        card.setBlocked(false);
        card.setName("card1");
        when(service.getAccountCard(1L, 1L)).thenReturn(card);
        this.mockMvc.perform(get("/accounts/1/cards/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(equalTo(card.getName())))
                .andExpect(jsonPath("$.blocked").value(equalTo(card.isBlocked())));
    }
    @Test
    void changeAccountCardBlockedStatus() throws Exception {
        CardDto card = new CardDto();
        card.setBlocked(true);
        card.setName("card1");
        when(service.updateCardBlockedStatus(1L, 1L, true)).thenReturn(card);
        this.mockMvc.perform(put("/accounts/1/cards/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"blocked\":true}")
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(equalTo(card.getName())))
                .andExpect(jsonPath("$.blocked").value(equalTo(card.isBlocked())));
    }

}
