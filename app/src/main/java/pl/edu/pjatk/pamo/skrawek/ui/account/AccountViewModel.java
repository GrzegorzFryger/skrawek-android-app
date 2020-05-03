package pl.edu.pjatk.pamo.skrawek.ui.account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Account;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Guardian;

public class AccountViewModel extends ViewModel {
    private String NAME_SURNAME_TEMPLATE = "%s %s";
    private MutableLiveData<Account> selectedAccount = new MutableLiveData<>();

    @Inject
    public AccountViewModel() {
    }

    public void setGuardian(Guardian guardian) {
        selectedAccount.setValue(guardian);
    }

    public LiveData<String> getName() {
        return Transformations.map(selectedAccount, Account::getName);
    }

    public LiveData<String> getSurname() {
        return Transformations.map(selectedAccount, Account::getSurname);
    }

    public LiveData<String> getAccountOwnerFullName() {
        return Transformations.map(selectedAccount, account ->
                String.format(NAME_SURNAME_TEMPLATE, account.getName(), account.getSurname())
        );
    }

    public LiveData<String> getCity() {
        return Transformations.map(selectedAccount, account ->
                String.format(NAME_SURNAME_TEMPLATE, account.getCity(), account.getPostalCode())
        );
    }

    public LiveData<String> getStreetNumber() {
        return Transformations.map(selectedAccount, Account::getStreetNumber);
    }

    public LiveData<String> getPhone() {
        return Transformations.map(selectedAccount, Account::getPhone);
    }

    public LiveData<String> getStatus() {
        return Transformations.map(selectedAccount, account -> account.getStatus().name());
    }

    public LiveData<Account> getSelectedAccount() {
        return selectedAccount;
    }
}
