package pl.edu.pjatk.pamo.skrawek.ui.children;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.UUID;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.repository.GuardianRepository;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Guardian;

/**
 * The type {@link ViewModel} class responsible to store and manage {@link Guardian} UI-related data.
 */
public class ChildrenSelectDialogViewModel extends ViewModel {
    private GuardianRepository guardianRepository;
    private MutableLiveData<UUID> guardianIdPublisher = new MutableLiveData<>();

    /**
     * Instantiates a new Children select dialog view model.
     *
     * @param guardianRepository the guardian repository
     */
    @Inject
    public ChildrenSelectDialogViewModel(GuardianRepository guardianRepository) {
        this.guardianRepository = guardianRepository;
    }

    /**
     * Gets guardian id publisher.
     *
     * @return the guardian id publisher
     */
    public MutableLiveData<UUID> getGuardianIdPublisher() {
        return guardianIdPublisher;
    }


    /**
     * Gets guardian live data.
     *
     * @return the guardian live data
     */
    public LiveData<Guardian> getGuardianLiveData() {
        return Transformations.switchMap(guardianIdPublisher, s -> this.guardianRepository.getGuardian(s));
    }

}
