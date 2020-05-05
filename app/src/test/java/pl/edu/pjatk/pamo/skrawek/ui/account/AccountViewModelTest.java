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

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import pl.edu.pjatk.pamo.skrawek.repository.AccountRepository;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Account;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.AccountStatus;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Guardian;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.PrivilegeType;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Role;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

    @Mock
    private Observer<Account> accountObserver;

    private MutableLiveData<Account> data;
    private AccountViewModel accountViewModel;
    private Guardian guardian;


    @Before
    public void setUp() {
        initMocks(this);

        this.guardian = new Guardian();
        guardian.setId(UUID.randomUUID());
        guardian.setName("Antoni");
        guardian.setSurname("Kowalski");
        guardian.setCity("Warszawa");
        guardian.setStreetNumber("Andersa 115");
        guardian.setPostalCode("15-234");
        guardian.setPhone("700800900");
        guardian.setStatus(AccountStatus.ACTIVE);
        guardian.setEmail("Some email");
        Set<Role> roles = new HashSet<>();
        Set<PrivilegeType> privileges = new HashSet<>();
        privileges.add(PrivilegeType.ADMINISTRATOR);
        roles.add(new Role("Administrator", privileges));
        guardian.setRoles(roles);

        accountViewModel = new AccountViewModel();

        data = new MutableLiveData<>();
        data.setValue(guardian);

        when(accountRepository.getMutableLiveData(anyString())).thenReturn(data);
    }

    @Test
    public void Should_GetFullName_From_AccountMutableLiveData() {
        //Given

        //When
        accountViewModel.setGuardian(guardian);
        LiveData<String> fullName = accountViewModel.getAccountOwnerFullName();
        fullName.observeForever(stringObserver);

        //Then
        assertEquals("Antoni Kowalski", fullName.getValue());
    }

    @Test
    public void Should_GetCity_From_AccountMutableLiveData() {
        //Given

        //When
        accountViewModel.setGuardian(guardian);
        LiveData<String> city = accountViewModel.getCity();
        city.observeForever(stringObserver);

        //Then
        assertEquals("Warszawa 15-234", city.getValue());
    }

    @Test
    public void Should_GetPhone_From_AccountMutableLiveData() {
        //Given

        //When
        accountViewModel.setGuardian(guardian);
        LiveData<String> phone = accountViewModel.getPhone();
        phone.observeForever(stringObserver);

        //Then
        assertEquals("700800900", phone.getValue());
    }

    @Test
    public void Should_GetStatus_From_AccountMutableLiveData() {
        //Given

        //When
        accountViewModel.setGuardian(guardian);
        LiveData<String> status = accountViewModel.getStatus();
        status.observeForever(stringObserver);

        //Then
        assertEquals("ACTIVE", status.getValue());
    }

    @Test
    public void Should_GetStreetNumber_From_AccountMutableLiveData() {
        //Given

        //When
        accountViewModel.setGuardian(guardian);
        LiveData<String> streetNumber = accountViewModel.getStreetNumber();
        streetNumber.observeForever(stringObserver);

        //Then
        assertEquals("Andersa 115", streetNumber.getValue());
    }

    @Test
    public void Should_GetName_From_AccountMutableLiveData() {
        //Given

        //When
        accountViewModel.setGuardian(guardian);
        LiveData<String> name = accountViewModel.getName();
        name.observeForever(stringObserver);

        //Then
        assertEquals("Antoni", name.getValue());
    }

    @Test
    public void Should_GetSurname_From_AccountMutableLiveData() {
        //Given

        //When
        accountViewModel.setGuardian(guardian);
        LiveData<String> surname = accountViewModel.getSurname();
        surname.observeForever(stringObserver);

        //Then
        assertEquals("Kowalski", surname.getValue());
    }

    @Test
    public void Should_GetSelectedAccount_From_AccountMutableLiveData() {
        //Given

        //When
        accountViewModel.setGuardian(guardian);
        LiveData<Account> account = accountViewModel.getSelectedAccount();

        //Then
        assertNotNull(account);
    }
}