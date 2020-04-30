package pl.edu.pjatk.pamo.skrawek.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import pl.edu.pjatk.pamo.skrawek.ui.DaggerViewModelFactory;
import pl.edu.pjatk.pamo.skrawek.ui.ViewModelKey;
import pl.edu.pjatk.pamo.skrawek.ui.absence.DayOffWorkViewModel;
import pl.edu.pjatk.pamo.skrawek.ui.account.AccountViewModel;
import pl.edu.pjatk.pamo.skrawek.ui.children.ChildrenSelectDialogViewModel;
import pl.edu.pjatk.pamo.skrawek.ui.children.ChildrenSelectViewModel;
import pl.edu.pjatk.pamo.skrawek.ui.finances.FinancesViewModel;

/**
 * This module is responsible for binding view models
 */
@Module
public abstract class ViewModelModule {
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(DaggerViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(ChildrenSelectViewModel.class)
    public abstract ViewModel provideChildrenSelectViewModel(ChildrenSelectViewModel childrenSelectViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FinancesViewModel.class)
    public abstract ViewModel provideFinancesViewModel(FinancesViewModel financesViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ChildrenSelectDialogViewModel.class)
    public abstract ViewModel provideChildrenSelectDialogViewModel(ChildrenSelectDialogViewModel childrenSelectDialogViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel.class)
    public abstract ViewModel provideAccountViewModel(AccountViewModel accountViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DayOffWorkViewModel.class)
    public abstract ViewModel provideDayOffWorkViewModel(DayOffWorkViewModel dayOffWorkViewModel);
}
