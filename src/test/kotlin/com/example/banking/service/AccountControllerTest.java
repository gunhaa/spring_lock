package com.example.banking.service;

import com.example.banking.controller.AccountController;
import com.example.banking.domain.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // 의존성 bean 추가
    @MockBean
    private AccountService accountService;

    @Test
    public void accounts_파라미터로_리턴값_테스트() throws Exception {

        Account expected = new Account("1", "gunha", 0L);

        String expectedJson = new ObjectMapper().writeValueAsString(expected);

        Mockito.when(accountService.createAccount("1", "gunha"))
                .thenReturn(expected);

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                        .param("id", "1").param("owner", "gunha"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedJson));
    }
}
