package pl.edu.pjatk.pamo.skrawek.ui;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import pl.edu.pjatk.pamo.skrawek.ui.account.AccountViewModel;
import pl.edu.pjatk.pamo.skrawek.ui.children.ChildrenSelectDialogViewModel;
import pl.edu.pjatk.pamo.skrawek.ui.children.ChildrenSelectViewModel;
import pl.edu.pjatk.pamo.skrawek.ui.finances.FinancesViewModel;

@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(DaggerViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(ChildrenSelectViewModel.class)
    abstract ViewModel provideChildrenSelectViewModel(ChildrenSelectViewModel childrenSelectViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FinancesViewModel.class)
    abstract ViewModel provideFinancesViewModel(FinancesViewModel financesViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ChildrenSelectDialogViewModel.class)
    abstract ViewModel provideChildrenSelectDialogViewModel(ChildrenSelectDialogViewModel childrenSelectDialogViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel.class)
    abstract ViewModel provideAccountViewModel(AccountViewModel accountViewModel);
}
