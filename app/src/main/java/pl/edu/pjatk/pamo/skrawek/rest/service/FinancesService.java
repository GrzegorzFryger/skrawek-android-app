package pl.edu.pjatk.pamo.skrawek.rest.service;

import java.util.UUID;

import pl.edu.pjatk.pamo.skrawek.rest.model.Balance;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static pl.edu.pjatk.pamo.skrawek.rest.config.RequestMappings.API_FINANCES;

/**
 * This service defines REST API urls for <strong>Finances</strong> module (FinancesController)
 */
public interface FinancesService {

    @GET(API_FINANCES + "balance/{childId}")
    Call<Balance> getBalanceForChild(@Path("childId") UUID childId);
}
