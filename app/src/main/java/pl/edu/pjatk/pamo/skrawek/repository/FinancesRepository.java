package pl.edu.pjatk.pamo.skrawek.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.rest.model.finances.IncomingPayment;
import pl.edu.pjatk.pamo.skrawek.rest.service.FinancesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinancesRepository {
    private static final String TAG = "FinancesRepository";

    private final FinancesService financesService;
    private final MutableLiveData<IncomingPayment> mutableLiveData = new MutableLiveData<>();

    @Inject
    public FinancesRepository(FinancesService guardianService) {
        this.financesService = guardianService;
    }

    public MutableLiveData<IncomingPayment> getIncomingPaymentsForChildren(UUID uuid) {
        Call<IncomingPayment> call = financesService.getAllIncomingPaymentsForChild(uuid);

        call.enqueue(new Callback<IncomingPayment>() {
            @Override
            public void onResponse(@NotNull Call<IncomingPayment> call, @NotNull Response<IncomingPayment> response) {
                IncomingPayment mBlogWrapper = response.body();
                if (mBlogWrapper != null) {
                    mutableLiveData.setValue(mBlogWrapper);
                }
            }

            @Override
            public void onFailure(@NotNull Call<IncomingPayment> call, @NotNull Throwable t) {
                Log.e(TAG, "Failed to get Incoming data for id: " + uuid);
            }
        });
        return mutableLiveData;
    }
}
