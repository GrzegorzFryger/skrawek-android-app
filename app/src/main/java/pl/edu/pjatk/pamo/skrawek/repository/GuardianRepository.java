package pl.edu.pjatk.pamo.skrawek.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Guardian;
import pl.edu.pjatk.pamo.skrawek.rest.service.GuardianService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This repository is responsible for retrieving data about guardians
 */
public class GuardianRepository {
    private static final String TAG = "GuardianRepository";

    private final GuardianService guardianService;
    private final MutableLiveData<Guardian> mutableLiveData = new MutableLiveData<>();

    /**
     * Instantiates a new Guardian repository.
     *
     * @param guardianService the guardian service
     */
    @Inject
    public GuardianRepository(GuardianService guardianService) {
        this.guardianService = guardianService;
    }


    /**
     * Returns the MutableLiveData <Guardian> list of object based on the account data obtained from
     * {@link  GuardianService}. Otherwise, return an empty MutableLiveData <Guardian> object.
     *
     * @param guardianId the guardian id
     * @return the {@link java.security.Guard}
     */
    public MutableLiveData<Guardian> getGuardian(UUID guardianId) {
        Call<Guardian> call = guardianService.getGuardian(guardianId);

        call.enqueue(new Callback<Guardian>() {
            @Override
            public void onResponse(@NotNull Call<Guardian> call, @NotNull Response<Guardian> response) {
                Guardian guardian = response.body();
                if (guardian != null) {
                    mutableLiveData.setValue(guardian);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Guardian> call, @NotNull Throwable t) {
                Log.e(TAG, "Failed to get Guardian data for id: " + guardianId);
            }
        });
        return mutableLiveData;
    }
}
