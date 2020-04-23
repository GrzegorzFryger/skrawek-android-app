package pl.edu.pjatk.pamo.skrawek.rest.auth;

import android.util.Log;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.rest.model.auth.LoginRequest;
import pl.edu.pjatk.pamo.skrawek.rest.model.auth.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Objects.requireNonNull;
import static pl.edu.pjatk.pamo.skrawek.rest.config.RequestMappings.API_AUTH;

public class AuthController implements Callback<LoginResponse> {
    private static final String TAG = "AuthController";

    private final AuthService authService;
    private final SessionManager sessionManager;

    @Inject
    public AuthController(AuthService authService, SessionManager sessionManager) {
        this.authService = authService;
        this.sessionManager = sessionManager;
    }

    @Override
    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
        if (response.isSuccessful()) {
            LoginResponse body = response.body();
            Log.i(TAG, "User authorized successfully");
            sessionManager.saveAuthToken("Bearer " + requireNonNull(body).getToken());
        } else {
            Log.e(TAG, "Failed to authorize user: " + response.message());
        }
    }

    @Override
    public void onFailure(Call<LoginResponse> call, Throwable t) {
        Log.e(TAG, requireNonNull(t.getMessage()));
    }

    public void authorize(String username, String password) {
        LoginRequest request = new LoginRequest(username, password);
        Log.i(TAG, "Calling endpoint: " + API_AUTH);
        Call<LoginResponse> call = authService.login(request);
        call.enqueue(this);
    }
}
