package pl.edu.pjatk.pamo.skrawek.ui;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import pl.edu.pjatk.pamo.skrawek.ui.children.ChildrenSelectDialogViewModel;
import pl.edu.pjatk.pamo.skrawek.ui.finances.FinancesViewModel;

@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(DaggerViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(ChildrenSelectDialogViewModel.class)
    abstract ViewModel provideVideoListViewModel(ChildrenSelectDialogViewModel childrenSelectDialogViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FinancesViewModel.class)
    abstract ViewModel provideFinancesViewModel(FinancesViewModel financesViewModel);
}
