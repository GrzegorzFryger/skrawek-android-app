package pl.edu.pjatk.pamo.skrawek;

import javax.inject.Singleton;

import dagger.Component;
import pl.edu.pjatk.pamo.skrawek.module.RestModule;
import pl.edu.pjatk.pamo.skrawek.module.UtilModule;
import pl.edu.pjatk.pamo.skrawek.module.ViewModelModule;
import pl.edu.pjatk.pamo.skrawek.module.ViewModule;
import pl.edu.pjatk.pamo.skrawek.ui.absence.AbsenceFragment;
import pl.edu.pjatk.pamo.skrawek.ui.account.AccountFragment;
import pl.edu.pjatk.pamo.skrawek.ui.children.ChildrenSelectDialog;
import pl.edu.pjatk.pamo.skrawek.ui.finances.FinancesFragment;
import pl.edu.pjatk.pamo.skrawek.ui.finances.IncomingPaymentsDetailsFragment;

/**
 * This Component is responsible for telling which components require injecting some dependencies
 */
@Singleton
@Component(modules = {RestModule.class, ViewModelModule.class, ViewModule.class, UtilModule.class})
public interface ApplicationComponent {

    void inject(LoginActivity loginActivity);

    void inject(ChildrenSelectDialog childrenSelectDialog);

    void inject(FinancesFragment financesFragment);

    void inject(IncomingPaymentsDetailsFragment incomingPaymentsDetailsFragment);

    void inject(AccountFragment accountFragment);

    void inject(AbsenceFragment absenceFragment);

    void inject(MainActivity mainActivity);

}
