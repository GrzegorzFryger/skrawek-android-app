package pl.edu.pjatk.pamo.skrawek;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Account;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Child;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Child> selectedChild = new MutableLiveData<>();
    private final MutableLiveData<Account> accountMutableLiveData = new MutableLiveData<>();

    public SharedViewModel() {
    }

    public void selectChild(Child child) {
        selectedChild.setValue(child);
    }

    public LiveData<Child> getSelectedChild() {
        return selectedChild;
    }

    public void loggedAccount(Account account) {
        accountMutableLiveData.setValue(account);
    }

    public LiveData<Account> getLoggedAccount() {
        return accountMutableLiveData;
    }
}
