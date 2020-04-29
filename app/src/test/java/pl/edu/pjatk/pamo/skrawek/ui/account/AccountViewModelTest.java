package pl.edu.pjatk.pamo.skrawek.ui.account;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;

import pl.edu.pjatk.pamo.skrawek.repository.AccountRepository;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Account;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.AccountStatus;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class AccountViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private AccountRepository accountRepository;

    private MutableLiveData<Account> data;

    private AccountViewModel accountViewModel;

    @Before
    public void setUp() {
        initMocks(this);
        accountViewModel = new AccountViewModel(accountRepository);

        data = new MutableLiveData<>();

        Account account = new Account();
        account.setName("Antoni");
        account.setSurname("Kowalski");
        account.setCity("Warszawa");
        account.setStreetNumber("Andersa 115");
        account.setPostalCode("15-234");
        account.setPhone("700800900");
        account.setStatus(AccountStatus.ACTIVE);

        data.setValue(account);
    }

    @Test
    public void Should_InitializeMutableLiveData() {
        //Given

        //When
        accountViewModel.initializeAccountData("Some email");

        //Then
        verify(accountRepository, only()).getMutableLiveData(anyString());
    }
}