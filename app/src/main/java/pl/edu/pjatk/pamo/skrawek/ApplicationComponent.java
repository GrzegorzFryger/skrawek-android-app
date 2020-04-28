package pl.edu.pjatk.pamo.skrawek;

import javax.inject.Singleton;

import dagger.Component;
import pl.edu.pjatk.pamo.skrawek.rest.RestModule;
import pl.edu.pjatk.pamo.skrawek.ui.ViewModelModule;
import pl.edu.pjatk.pamo.skrawek.ui.account.AccountFragment;
import pl.edu.pjatk.pamo.skrawek.ui.children.ChildrenSelectDialog;

@Singleton
@Component(modules = {RestModule.class, ViewModelModule.class})
public interface ApplicationComponent {

    void inject(LoginActivity loginActivity);

    void inject(ChildrenSelectDialog childrenSelectDialog);

    void inject(AccountFragment accountFragment);
}
