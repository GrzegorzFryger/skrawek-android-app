package pl.edu.pjatk.pamo.skrawek;

import javax.inject.Singleton;

import dagger.Component;
import pl.edu.pjatk.pamo.skrawek.module.RestModule;
import pl.edu.pjatk.pamo.skrawek.module.ViewModelModule;
import pl.edu.pjatk.pamo.skrawek.ui.account.AccountFragment;
import pl.edu.pjatk.pamo.skrawek.ui.children.ChildrenSelectDialog;
import pl.edu.pjatk.pamo.skrawek.ui.finances.FinancesFragment;
import pl.edu.pjatk.pamo.skrawek.ui.finances.IncomingPaymentsDetailsFragment;

@Singleton
@Component(modules = {RestModule.class, ViewModelModule.class})
public interface ApplicationComponent {

    void inject(LoginActivity loginActivity);

    void inject(ChildrenSelectDialog childrenSelectDialog);

    void inject(FinancesFragment financesFragment);

    void inject(IncomingPaymentsDetailsFragment incomingPaymentsDetailsFragment);

    void inject(AccountFragment accountFragment);

    void inject(MainActivity mainActivity);

}
