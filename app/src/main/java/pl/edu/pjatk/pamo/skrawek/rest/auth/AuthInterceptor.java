package pl.edu.pjatk.pamo.skrawek.rest.auth;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static pl.edu.pjatk.pamo.skrawek.rest.auth.AuthConstants.AUTHORIZATION_HEADER;
import static pl.edu.pjatk.pamo.skrawek.rest.auth.SessionManager.getAuthToken;

public class AuthInterceptor implements Interceptor {

    public AuthInterceptor() {
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        requestBuilder.addHeader(AUTHORIZATION_HEADER, getAuthToken());
        return chain.proceed(requestBuilder.build());
    }
}
