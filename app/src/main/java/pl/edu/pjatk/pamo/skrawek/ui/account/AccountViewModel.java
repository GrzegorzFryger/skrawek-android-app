package pl.edu.pjatk.pamo.skrawek.ui.account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.repository.AccountRepository;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Account;

public class AccountViewModel extends ViewModel {
    private String NAME_SURNAME_TEMPLATE = "%s %s";
    private final AccountRepository accountRepository;
    private MutableLiveData<Account> selectedAccount = new MutableLiveData<>();

    @Inject
    public AccountViewModel(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void initializeAccountData(String email) {
        selectedAccount = accountRepository.getMutableLiveData(email);
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
}
