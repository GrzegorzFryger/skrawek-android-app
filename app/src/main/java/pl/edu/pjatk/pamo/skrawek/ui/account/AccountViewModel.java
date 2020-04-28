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
    private MutableLiveData<String> emailAccount = new MutableLiveData<>();

    @Inject
    public AccountViewModel(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public MutableLiveData<String> getEmailAccount() {
        return emailAccount;
    }


    public LiveData<Account> getAccountOwnerFullName() {
        return Transformations.switchMap(emailAccount, s -> this.accountRepository.getMutableLiveData(s));
    }
}
