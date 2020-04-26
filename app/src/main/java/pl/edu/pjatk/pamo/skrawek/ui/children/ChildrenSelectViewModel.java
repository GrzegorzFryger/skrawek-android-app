package pl.edu.pjatk.pamo.skrawek.ui.children;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.UUID;

import pl.edu.pjatk.pamo.skrawek.repository.GuardianRepository;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Guardian;

public class ChildrenSelectViewModel extends ViewModel {
    private MediatorLiveData<UUID> dataSubject = new MediatorLiveData<>();
    private MutableLiveData<UUID> dataPublisher = new MutableLiveData<>();
    private LiveData<Guardian> guardianLiveData = new MutableLiveData<>();
    private GuardianRepository guardianRepository;

    public ChildrenSelectViewModel() {
        this.guardianRepository = new GuardianRepository();

    }

    public LiveData<Guardian> getGuardian() {
        return  guardianLiveData;
    }

    public MediatorLiveData<UUID> getDataSubject() {
        return dataSubject;

    }

    public MutableLiveData<UUID> getDataPublisher() {
        return dataPublisher;
    }

    private void onPublishData() {
        dataSubject.addSource(
                dataPublisher,
                s -> this.guardianLiveData = this.guardianRepository.getMutableLiveData(s)
        );
    }
}
