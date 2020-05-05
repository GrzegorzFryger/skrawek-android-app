package pl.edu.pjatk.pamo.skrawek.ui.account;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;

import pl.edu.pjatk.pamo.skrawek.repository.AccountRepository;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Account;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.AccountStatus;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Guardian;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AccountViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private Observer<String> stringObserver;

    private MutableLiveData<Account> data;
    private AccountViewModel accountViewModel;
    private Guardian account;


    @Before
    public void setUp() {
        initMocks(this);

        this.account = new Guardian();
        account.setName("Antoni");
        account.setSurname("Kowalski");
        account.setCity("Warszawa");
        account.setStreetNumber("Andersa 115");
        account.setPostalCode("15-234");
        account.setPhone("700800900");
        account.setStatus(AccountStatus.ACTIVE);

        accountViewModel = new AccountViewModel();

        data = new MutableLiveData<>();
        data.setValue(account);

        when(accountRepository.getAccount(anyString())).thenReturn(data);
    }

    @Test
    public void Should_GetFullName_From_AccountMutableLiveData() {
        //Given

        //When
        accountViewModel.setGuardian(account);
        LiveData<String> fullName = accountViewModel.getAccountOwnerFullName();
        fullName.observeForever(stringObserver);

        //Then
        assertEquals("Antoni Kowalski", fullName.getValue());
    }

    @Test
    public void Should_GetCity_From_AccountMutableLiveData() {
        //Given

        //When
        accountViewModel.setGuardian(account);
        LiveData<String> city = accountViewModel.getCity();
        city.observeForever(stringObserver);

        //Then
        assertEquals("Warszawa 15-234", city.getValue());
    }

    @Test
    public void Should_GetPhone_From_AccountMutableLiveData() {
        //Given

        //When
        accountViewModel.setGuardian(account);
        LiveData<String> phone = accountViewModel.getPhone();
        phone.observeForever(stringObserver);

        //Then
        assertEquals("700800900", phone.getValue());
    }

    @Test
    public void Should_GetStatus_From_AccountMutableLiveData() {
        //Given

        //When
        accountViewModel.setGuardian(account);
        LiveData<String> status = accountViewModel.getStatus();
        status.observeForever(stringObserver);

        //Then
        assertEquals("ACTIVE", status.getValue());
    }

    @Test
    public void Should_GetStreetNumber_From_AccountMutableLiveData() {
        //Given

        //When
        accountViewModel.setGuardian(account);
        LiveData<String> streetNumber = accountViewModel.getStreetNumber();
        streetNumber.observeForever(stringObserver);

        //Then
        assertEquals("Andersa 115", streetNumber.getValue());
    }
}