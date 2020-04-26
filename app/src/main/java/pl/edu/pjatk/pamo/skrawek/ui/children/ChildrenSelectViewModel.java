package pl.edu.pjatk.pamo.skrawek.ui.children;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.UUID;

import pl.edu.pjatk.pamo.skrawek.repository.GuardianRepository;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Guardian;

public class ChildrenSelectViewModel extends ViewModel {
    private MutableLiveData<String> data = new MediatorLiveData<>();
    private GuardianRepository guardianRepository;
    public LiveData<Guardian> guardianLiveData;

    public ChildrenSelectViewModel() {
        this.guardianRepository = new GuardianRepository();
        //todo temporary
        this.guardianLiveData = guardianRepository.getMutableLiveData(UUID.fromString("26d506c9-c44a-4b58-a4a8-0e3209e96c84"));
    }

    public LiveData<Guardian> getGuardian() {
        return guardianLiveData;
    }

}
