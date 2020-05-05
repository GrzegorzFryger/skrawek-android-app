package pl.edu.pjatk.pamo.skrawek.rest.service;

import java.util.List;
import java.util.UUID;

import pl.edu.pjatk.pamo.skrawek.rest.model.finances.Balance;
import pl.edu.pjatk.pamo.skrawek.rest.model.finances.IncomingPayment;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static pl.edu.pjatk.pamo.skrawek.rest.config.RequestMappings.API_FINANCES;
import static pl.edu.pjatk.pamo.skrawek.rest.config.RequestMappings.API_RECEIVABLES;

/**
 * This service defines REST API urls for <strong>Finances</strong> module (FinancesController)
 */
public interface FinancesService {


    /**
     * Prepare call rest Api to fetch {@link Balance} data
     *
     * @param childId the child id
     * @return the balance for child
     */
    @GET(API_FINANCES + "balance/{childId}")
    Call<Balance> getBalanceForChild(@Path("childId") UUID childId);


    /**
     * Prepare call rest Api to fetch {@link IncomingPayment} data
     *
     * @param childId the child id
     * @return the all incoming payments for child
     */
    @GET(API_RECEIVABLES + "payments/child/{childId}")
    Call<List<IncomingPayment>> getAllIncomingPaymentsForChild(@Path("childId") UUID childId);
}
