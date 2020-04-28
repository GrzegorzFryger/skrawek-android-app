package pl.edu.pjatk.pamo.skrawek.rest;

import dagger.Module;
import dagger.Provides;
import pl.edu.pjatk.pamo.skrawek.rest.auth.AuthService;
import pl.edu.pjatk.pamo.skrawek.rest.service.AccountService;
import pl.edu.pjatk.pamo.skrawek.rest.service.FinancesService;
import pl.edu.pjatk.pamo.skrawek.rest.service.GuardianService;

import static pl.edu.pjatk.pamo.skrawek.rest.service.ServiceGenerator.createService;

@Module
public class RestModule {

    @Provides
    public AuthService authService() {
        return createService(AuthService.class);
    }

    @Provides
    public AccountService accountService() {
        return createService(AccountService.class);
    }

    @Provides
    public GuardianService guardianService() {
        return createService(GuardianService.class);
    }

    @Provides
    public FinancesService financesService() {
        return createService(FinancesService.class);
    }
}
