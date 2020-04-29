package pl.edu.pjatk.pamo.skrawek;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.rest.auth.AuthService;
import pl.edu.pjatk.pamo.skrawek.rest.model.auth.LoginRequest;
import pl.edu.pjatk.pamo.skrawek.rest.model.auth.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Objects.requireNonNull;
import static pl.edu.pjatk.pamo.skrawek.MyApplication.getStringFromRes;
import static pl.edu.pjatk.pamo.skrawek.rest.auth.SessionManager.saveAuthToken;
import static pl.edu.pjatk.pamo.skrawek.rest.auth.SessionManager.saveEmail;

/**
 * This activity is entry point of application.
 * User is required to provide proper credentials in order to access
 * all of applications functions.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";

    @Inject
    AuthService authService;

    private TextInputEditText emailInput;
    private TextInputEditText passwordInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_fragment);

        ((MyApplication) getApplication()).getAppComponent().inject(this);

        Button signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(this);

        emailInput = findViewById(R.id.enterEmail);
        passwordInput = findViewById(R.id.enterPassword);

        this.setTransparentTitleBar(getWindow());
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
                    saveEmail(email);
                    navigateToMainFragment();
                } else {
                    Log.e(TAG, "Failed to authorize user: " + request.getUsername());
                    showErrorMessageUsingSnackbar(getStringFromRes(R.string.rest_login_fail));
                }
            }

            @Override
            public void onFailure(@NotNull Call<LoginResponse> call, @NotNull Throwable t) {
                Log.e(TAG, requireNonNull(t.getMessage()));
                showErrorMessageUsingSnackbar(getStringFromRes(R.string.rest_call_fail));
            }
        });
    }

    protected void navigateToMainFragment() {
        startActivity(new Intent(this, MainActivity.class));
    }

    protected void setTransparentTitleBar(Window window) {
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.TRANSPARENT);

        // set color icon to dark
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    protected void showErrorMessageUsingSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        view.setBackgroundColor(ContextCompat.getColor(this, R.color.snackbar_red));
        TextView snackTextView = view.findViewById(R.id.snackbar_text);
        snackTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        snackTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        snackbar.show();
    }
}
