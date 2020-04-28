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
    private MutableLiveData<Account> accountMutableLiveData = new MutableLiveData<>();

    @Inject
    public AccountViewModel(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public MutableLiveData<Account> saveAccountLiveData(String email) {
        accountMutableLiveData = accountRepository.getMutableLiveData(email);
        return accountMutableLiveData;
    }

    public LiveData<String> getAccountOwnerFullName() {
        return Transformations.map(accountMutableLiveData, account ->
                String.format(NAME_SURNAME_TEMPLATE, account.getName(), account.getSurname())
        );
    }
}
