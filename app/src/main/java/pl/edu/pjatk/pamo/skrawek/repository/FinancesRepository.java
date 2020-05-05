package pl.edu.pjatk.pamo.skrawek.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.rest.model.finances.Balance;
import pl.edu.pjatk.pamo.skrawek.rest.model.finances.IncomingPayment;
import pl.edu.pjatk.pamo.skrawek.rest.service.FinancesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This repository is responsible for retrieving financial data
 */
public class FinancesRepository {
    private static final String TAG = "FinancesRepository";

    private final FinancesService financesService;
    private final MutableLiveData<List<IncomingPayment>> listMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Balance> balance = new MutableLiveData<>();

    /**
     * Instantiates a new Finances repository.
     *
     * @param guardianService the guardian service
     */
    @Inject
    public FinancesRepository(FinancesService guardianService) {
        this.financesService = guardianService;
    }


    /**
     * Returns the MutableLiveData <IncomingPayment> list of object based on the account data obtained from
     * {@link  FinancesService}. Otherwise, return an empty MutableLiveData <IncomingPayment> object.
     *
     * @param uuid the uuid
     * @return the {@link IncomingPayment}
     */
    public MutableLiveData<List<IncomingPayment>> getIncomingPaymentsForChildren(UUID uuid) {
        Call<List<IncomingPayment>> call = financesService.getAllIncomingPaymentsForChild(uuid);

        call.enqueue(new Callback<List<IncomingPayment>>() {
            @Override
            public void onResponse(@NotNull Call<List<IncomingPayment>> call, @NotNull Response<List<IncomingPayment>> response) {
                List<IncomingPayment> incomingPayments = response.body();
                if (incomingPayments != null) {
                    listMutableLiveData.setValue(incomingPayments);
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<IncomingPayment>> call, @NotNull Throwable t) {
                Log.e(TAG, "Failed to get Incoming data for id: " + uuid);
            }
        });
        return listMutableLiveData;
    }


    /**
     * Returns the MutableLiveData <Balance> list of object based on the account data obtained from
     * {@link  FinancesService}. Otherwise, return an empty MutableLiveData <Balance> object.
     *
     * @param uuid the uuid
     * @return the {@link Balance}
     */
    public MutableLiveData<Balance> getBalance(UUID uuid) {
        Call<Balance> call = financesService.getBalanceForChild(uuid);

        call.enqueue(new Callback<Balance>() {
            @Override
            public void onResponse(@NotNull Call<Balance> call, @NotNull Response<Balance> response) {
                Balance balance = response.body();
                if (balance != null) {
                    FinancesRepository.this.balance.setValue(balance);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Balance> call, @NotNull Throwable t) {
                Log.e(TAG, "Failed to get Incoming data for id: " + uuid);
            }
        });
        return balance;
    }
}
