package pl.edu.pjatk.pamo.skrawek.rest.service.impl;

import java.util.UUID;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.rest.model.Balance;
import pl.edu.pjatk.pamo.skrawek.rest.service.FinancesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BalanceController implements Callback<Balance> {
    private final FinancesService financesService;

    @Inject
    public BalanceController(Retrofit retrofit) {
        this.financesService = retrofit.create(FinancesService.class);
    }

    public void start(UUID childId) {
        Call<Balance> call = financesService.getBalanceForChild(childId);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Balance> call, Response<Balance> response) {
        if (response.isSuccessful()) {
            Balance balance = response.body();
            System.out.println(balance);
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<Balance> call, Throwable t) {
        t.printStackTrace();
    }
}
