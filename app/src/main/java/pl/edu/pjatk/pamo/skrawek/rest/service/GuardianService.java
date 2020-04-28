package pl.edu.pjatk.pamo.skrawek.rest.service;

import java.util.UUID;

import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Guardian;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static pl.edu.pjatk.pamo.skrawek.rest.config.RequestMappings.API_ACCOUNT;

public interface GuardianService {

    @GET(API_ACCOUNT + "guardian/{guardianId}")
    Call<Guardian> getGuardian(@Path("guardianId") UUID guardianId);

}
