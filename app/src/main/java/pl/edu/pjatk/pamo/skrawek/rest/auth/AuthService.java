package pl.edu.pjatk.pamo.skrawek.rest.auth;

import pl.edu.pjatk.pamo.skrawek.rest.model.auth.LoginRequest;
import pl.edu.pjatk.pamo.skrawek.rest.model.auth.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static pl.edu.pjatk.pamo.skrawek.rest.config.RequestMappings.API_AUTH;

public interface AuthService {

    @POST(API_AUTH)
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
