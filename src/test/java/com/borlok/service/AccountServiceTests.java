package com.borlok.service;

import com.borlok.model.Account;
import com.borlok.model.AccountStatus;
import org.junit.*;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTests {
    @Mock
    private Account account;

    @Mock
    private Account returnedAccount;

    @Mock
    private List<Account> accounts;

    @Spy
    private AccountService accountService;

    @Before
    public void setUp() {
        String name = "Check";
        AccountStatus accountStatus = AccountStatus.ACTIVE;

        when(account.getName()).thenReturn(name);
        when(account.getStatus()).thenReturn(accountStatus);
    }

    @Test
    public void getAccountTests() {
        assertEquals("Check", account.getName());
        assertEquals(AccountStatus.ACTIVE, account.getStatus());
        assertEquals(0,account.getId());
    }

    @Test
    public void createTest () {
        doReturn(returnedAccount).when(accountService).create(account);
        assertEquals(returnedAccount, accountService.create(account));
    }

    @Test
    public void getAllTest () {
        doReturn(accounts).when(accountService).getAll();
        assertEquals(accounts, accountService.getAll());
    }

    @Test
    public void updateTest () {
        doReturn(returnedAccount).when(accountService).update(account,15);
        assertEquals(returnedAccount, accountService.update(account, 15));
    }

}
