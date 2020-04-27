package pl.edu.pjatk.pamo.skrawek.rest.service;

import org.junit.Test;

import pl.edu.pjatk.pamo.skrawek.rest.auth.AuthService;

import static org.junit.Assert.assertNotNull;
import static pl.edu.pjatk.pamo.skrawek.rest.service.ServiceGenerator.createService;

public class ServiceGeneratorTest {

    @Test
    public void Should_CreateAuthService() {
        //Given

        //When
        AuthService service = createService(AuthService.class);

        //Then
        assertNotNull(service);
    }

    @Test
    public void Should_CreateFinancesService() {
        //Given

        //When
        FinancesService service = createService(FinancesService.class);

        //Then
        assertNotNull(service);
    }

    @Test
    public void Should_CreateAccountService() {
        //Given

        //When
        AccountService service = createService(AccountService.class);

        //Then
        assertNotNull(service);
    }

}