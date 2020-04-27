package pl.edu.pjatk.pamo.skrawek;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import pl.edu.pjatk.pamo.skrawek.rest.auth.AuthService;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Account;
import pl.edu.pjatk.pamo.skrawek.rest.model.auth.LoginRequest;
import pl.edu.pjatk.pamo.skrawek.rest.model.auth.LoginResponse;
import pl.edu.pjatk.pamo.skrawek.rest.service.AccountService;
import pl.edu.pjatk.pamo.skrawek.rest.service.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Objects.requireNonNull;
import static pl.edu.pjatk.pamo.skrawek.rest.auth.SessionManager.saveAccountData;
import static pl.edu.pjatk.pamo.skrawek.rest.auth.SessionManager.saveAuthToken;

/**
 * This activity is entry point of application.
 * User is required to provide proper credentials in order to access
 * all of applications functions.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";

    private AccountService accountService;
    private AuthService authService;

    private TextInputEditText emailInput;
    private TextInputEditText passwordInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_fragment);

        Button signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(this);

        emailInput = findViewById(R.id.enterEmail);
        passwordInput = findViewById(R.id.enterPassword);

        accountService = ServiceGenerator.createService(AccountService.class);
        authService = ServiceGenerator.createService(AuthService.class);
    }

    @Override
    public void onClick(View v) {
        String email = requireNonNull(emailInput.getText()).toString();
        String password = requireNonNull(passwordInput.getText()).toString();

        login(email, password);
    }

    public void login(String email, String password) {
        LoginRequest request = new LoginRequest(email, password);
        Call<LoginResponse> call = authService.login(request);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NotNull Call<LoginResponse> call, @NotNull Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "User: " + request.getUsername() + " authorized successfully");
                    saveAuthToken("Bearer " + requireNonNull(response.body()).getToken());
                    getAccountDetails(email);
                } else {
                    Log.e(TAG, "Failed to authorize user: " + request.getUsername());
                }
            }

            @Override
            public void onFailure(@NotNull Call<LoginResponse> call, @NotNull Throwable t) {
                Log.e(TAG, requireNonNull(t.getMessage()));
            }
        });
    }

    public void getAccountDetails(String email) {
        Call<Account> call = accountService.getAccountDetails(email);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(@NotNull Call<Account> call, @NotNull Response<Account> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "User id: " + requireNonNull(response.body()).getId());
                    saveAccountData(response.body());
                    navigateToMainFragment();
                } else {
                    Log.e(TAG, "Failed to get account details for user: " + email);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Account> call, @NotNull Throwable t) {
                Log.e(TAG, requireNonNull(t.getMessage()));
            }
        });
    }

    private void navigateToMainFragment() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
