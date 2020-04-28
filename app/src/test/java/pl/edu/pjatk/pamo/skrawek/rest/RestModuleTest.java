package pl.edu.pjatk.pamo.skrawek.rest;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pjatk.pamo.skrawek.rest.auth.AuthService;
import pl.edu.pjatk.pamo.skrawek.rest.service.AccountService;
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
}