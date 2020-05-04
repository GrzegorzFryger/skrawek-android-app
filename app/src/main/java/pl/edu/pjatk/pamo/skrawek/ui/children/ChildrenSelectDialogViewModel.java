package pl.edu.pjatk.pamo.skrawek.ui.children;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.UUID;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.repository.GuardianRepository;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Guardian;

public class ChildrenSelectDialogViewModel extends ViewModel {
    private GuardianRepository guardianRepository;
    private MutableLiveData<UUID> guardianIdPublisher = new MutableLiveData<>();

    @Inject
    public ChildrenSelectDialogViewModel(GuardianRepository guardianRepository) {
        this.guardianRepository = guardianRepository;
    }

    public MutableLiveData<UUID> getGuardianIdPublisher() {
        return guardianIdPublisher;
    }


    public LiveData<Guardian> getGuardianLiveData() {
        return Transformations.switchMap(guardianIdPublisher, s -> this.guardianRepository.getGuardian(s));
    }

}
