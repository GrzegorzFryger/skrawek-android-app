package pl.edu.pjatk.pamo.skrawek.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.UUID;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Guardian;
import pl.edu.pjatk.pamo.skrawek.rest.service.GuardianService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuardianRepository {
    private final GuardianService guardianService;
    private MutableLiveData<Guardian> mutableLiveData = new MutableLiveData<>();

    @Inject
    public GuardianRepository(GuardianService guardianService) {
        this.guardianService = guardianService;
    }

    public MutableLiveData<Guardian> getMutableLiveData(UUID guardianId) {
        Call<Guardian> call = guardianService.getGuardian(guardianId);

        call.enqueue(new Callback<Guardian>() {
            @Override
            public void onResponse(Call<Guardian> call, Response<Guardian> response) {
                Guardian mBlogWrapper = response.body();
                if (mBlogWrapper != null) {
                    mutableLiveData.setValue(mBlogWrapper);
                }
            }

            @Override
            public void onFailure(Call<Guardian> call, Throwable t) {
            }
        });
        return mutableLiveData;
    }
}
