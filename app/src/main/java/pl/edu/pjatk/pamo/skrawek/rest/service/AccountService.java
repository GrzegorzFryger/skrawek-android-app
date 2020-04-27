package pl.edu.pjatk.pamo.skrawek.rest.service;

import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Account;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static pl.edu.pjatk.pamo.skrawek.rest.config.RequestMappings.API_ACCOUNT;

public interface AccountService {

    @GET(API_ACCOUNT + "user")
    Call<Account> getAccountDetails(@Query("email") String email);
}
