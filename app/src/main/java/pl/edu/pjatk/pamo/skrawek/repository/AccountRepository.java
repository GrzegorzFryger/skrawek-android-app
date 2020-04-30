package pl.edu.pjatk.pamo.skrawek.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Account;
import pl.edu.pjatk.pamo.skrawek.rest.service.AccountService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This repository is responsible for retrieving account data
 */
public class AccountRepository {
    private static final String TAG = "AccountRepository";

    private final AccountService accountService;
    private final MutableLiveData<Account> mutableLiveData = new MutableLiveData<>();

    @Inject
    public AccountRepository(AccountService accountService) {
        this.accountService = accountService;
    }

    public MutableLiveData<Account> getMutableLiveData(String email) {
        Call<Account> call = accountService.getAccountDetails(email);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(@NotNull Call<Account> call, @NotNull Response<Account> response) {
                Account account = response.body();
                if (account != null) {
                    mutableLiveData.setValue(account);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Account> call, @NotNull Throwable t) {
                Log.e(TAG, "Failed to get Account data for account with email: " + email);
            }
        });
        return mutableLiveData;
    }
}
