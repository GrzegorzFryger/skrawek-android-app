package pl.edu.pjatk.pamo.skrawek.repository;

import androidx.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Account;
import pl.edu.pjatk.pamo.skrawek.rest.service.AccountService;
import retrofit2.Call;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AccountRepositoryTest {
    @Mock
    private AccountService accountService;

    private AccountRepository accountRepository;

    @Before
    public void setUp() {
        initMocks(this);
        accountRepository = new AccountRepository(accountService);
    }

    @Test
    public void Should_GetMutableLiveDataFor_AccountDetails() {
        //Given
        Call call = mock(Call.class);

        //When
        when(accountService.getAccountDetails(anyString())).thenReturn(call);
        MutableLiveData<Account> result = accountRepository.getAccount("Some email");

        //Then
        assertNotNull(result);
    }
}