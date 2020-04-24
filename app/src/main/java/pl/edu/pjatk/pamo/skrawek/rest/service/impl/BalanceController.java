package pl.edu.pjatk.pamo.skrawek.rest.service.impl;

import android.util.Log;

import java.util.Objects;
import java.util.UUID;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.rest.model.Balance;
import pl.edu.pjatk.pamo.skrawek.rest.service.FinancesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BalanceController implements Callback<Balance> {
    private static final String TAG = "BalanceController";

    private final FinancesService financesService;

    @Inject
    public BalanceController(FinancesService financesService) {
        this.financesService = financesService;
    }

    @Override
    public void onResponse(Call<Balance> call, Response<Balance> response) {
        if (response.isSuccessful()) {
            Balance balance = response.body();
            Log.i(TAG, "Retrieved balance: " +
                    Objects.requireNonNull(balance).getBalance().toString());
        } else {
            Log.e(TAG, response.message());
        }
    }

    @Override
    public void onFailure(Call<Balance> call, Throwable t) {
        Log.e(TAG, Objects.requireNonNull(t.getMessage()));
    }

    public void getBalanceForChild(UUID childId) {
        Call<Balance> call = financesService.getBalanceForChild(childId);
        call.enqueue(this);
    }
}
