package pl.edu.pjatk.pamo.skrawek.module;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pjatk.pamo.skrawek.repository.AccountRepository;
import pl.edu.pjatk.pamo.skrawek.repository.CalendarRepository;
import pl.edu.pjatk.pamo.skrawek.repository.FinancesRepository;
import pl.edu.pjatk.pamo.skrawek.repository.GuardianRepository;
import pl.edu.pjatk.pamo.skrawek.rest.auth.AuthService;
import pl.edu.pjatk.pamo.skrawek.rest.service.AccountService;
import pl.edu.pjatk.pamo.skrawek.rest.service.CalendarService;
import pl.edu.pjatk.pamo.skrawek.rest.service.FinancesService;
import pl.edu.pjatk.pamo.skrawek.rest.service.GuardianService;

import static org.junit.Assert.assertNotNull;

public class RestModuleTest {

    private RestModule restModule;

    @Before
    public void setUp() {
        restModule = new RestModule();
    }


    @Test
    public void Should_CreateAuthService() {
        //Given

        //When
        AuthService result = restModule.authService();

        //Then
        assertNotNull(result);
    }

    @Test
    public void Should_CreateFinancesService() {
        //Given

        //When
        FinancesService result = restModule.financesService();

        //Then
        assertNotNull(result);
    }


    @Test
    public void Should_CreateGuardianService() {
        //Given

        //When
        GuardianService result = restModule.guardianService();

        //Then
        assertNotNull(result);
    }


    @Test
    public void Should_CreateAccountService() {
        //Given

        //When
        AccountService result = restModule.accountService();

        //Then
        assertNotNull(result);
    }

    @Test
    public void Should_CreateCalendarService() {
        //Given

        //When
        CalendarService result = restModule.calendarService();

        //Then
        assertNotNull(result);
    }


    @Test
    public void Should_CreateGuardianRepository() {
        //Given

        //When
        GuardianRepository guardianRepository = restModule.guardianRepository();

        //Then
        assertNotNull(guardianRepository);
    }

    @Test
    public void Should_CreateFinancesRepository() {
        //Given

        //When
        FinancesRepository financesRepository = restModule.financesRepository();

        //Then
        assertNotNull(financesRepository);
    }

    @Test
    public void Should_CreateAccountRepository() {
        //Given

        //When
        AccountRepository accountRepository = restModule.accountRepository();

        //Then
        assertNotNull(accountRepository);
    }

    @Test
    public void Should_CreateCalendarRepository() {
        //Given

        //When
        CalendarRepository calendarRepository = restModule.calendarRepository();

        //Then
        assertNotNull(calendarRepository);
    }
}