package eu.greyson.sample;

import eu.greyson.sample.general.dto.AccountDto;
import eu.greyson.sample.general.model.Account;
import eu.greyson.sample.general.rest.impl.AccountControllerImpl;
import eu.greyson.sample.general.service.AccountService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
                .andExpect(content().string("[{\"name\":\"account1\",\"balance\":12}]"));
    }

}
