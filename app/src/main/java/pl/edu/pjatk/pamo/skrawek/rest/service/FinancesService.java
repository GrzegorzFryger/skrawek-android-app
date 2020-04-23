package pl.edu.pjatk.pamo.skrawek.rest.service;

import java.util.UUID;

import pl.edu.pjatk.pamo.skrawek.rest.model.Balance;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static pl.edu.pjatk.pamo.skrawek.rest.config.RequestMappings.API_FINANCES;

public interface FinancesService {
    String GET_BALANCE_FOR_ALL_CHILD = API_FINANCES + "balance/{childId}";

    @GET(GET_BALANCE_FOR_ALL_CHILD)
    Call<Balance> getBalanceForChild(@Path("childId") UUID childId);
}
