package pl.edu.pjatk.pamo.skrawek.module;

import dagger.Module;
import dagger.Provides;
import pl.edu.pjatk.pamo.skrawek.repository.CalendarRepository;
import pl.edu.pjatk.pamo.skrawek.repository.FinancesRepository;
import pl.edu.pjatk.pamo.skrawek.repository.AccountRepository;
import pl.edu.pjatk.pamo.skrawek.repository.GuardianRepository;
import pl.edu.pjatk.pamo.skrawek.rest.auth.AuthService;
import pl.edu.pjatk.pamo.skrawek.rest.service.AccountService;
import pl.edu.pjatk.pamo.skrawek.rest.service.CalendarService;
import pl.edu.pjatk.pamo.skrawek.rest.service.FinancesService;
import pl.edu.pjatk.pamo.skrawek.rest.service.GuardianService;

import static pl.edu.pjatk.pamo.skrawek.rest.service.ServiceGenerator.createService;

@Module
/**
 * This module is responsible for injecting services and repositories to components, that require them
 */
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

    @Provides
    public CalendarService calendarService() {
        return createService(CalendarService.class);
    }

    @Provides
    public GuardianRepository guardianRepository() {
        return new GuardianRepository(guardianService());
    }

    @Provides
    public FinancesRepository financesRepository() {
        return new FinancesRepository(financesService());
    }
  
    @Provides
    public AccountRepository accountRepository() {
        return new AccountRepository(accountService());
    }

    @Provides
    public CalendarRepository calendarRepository() {
        return new CalendarRepository(calendarService());
    }
}
