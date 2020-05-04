package pl.edu.pjatk.pamo.skrawek;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.repository.GuardianRepository;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Account;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Child;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Guardian;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<Child> selectedChild = new MutableLiveData<>();
    private MutableLiveData<Account> accountLiveData = new MutableLiveData<>();

    private LiveData<Guardian> guardianMutableLiveData;
    private final GuardianRepository guardianRepository;

    @Inject
    public SharedViewModel(GuardianRepository guardianRepository) {
        this.guardianRepository = guardianRepository;

        this.guardianMutableLiveData = Transformations.switchMap(
                accountLiveData,
                s -> this.guardianRepository.getGuardian(s.getId())
        );
    }

    public void selectChild(Child child) {
        selectedChild.setValue(child);
    }

    public LiveData<Child> getSelectedChild() {
        return selectedChild;
    }

    public void setLoggedAccount(Account account) {
        this.accountLiveData.setValue(account);
    }

    public LiveData<Guardian> getLoggedGuardian() {
        return guardianMutableLiveData;
    }
}

