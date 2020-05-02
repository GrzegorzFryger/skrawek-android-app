package pl.edu.pjatk.pamo.skrawek.ui.absence;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.repository.CalendarRepository;
import pl.edu.pjatk.pamo.skrawek.repository.GuardianRepository;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Guardian;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.Absence;

public class AbsenceViewModel extends ViewModel {
    private final CalendarRepository calendarRepository;
    private final GuardianRepository guardianRepository;

    @Inject
    public AbsenceViewModel(CalendarRepository calendarRepository, GuardianRepository guardianRepository) {
        this.calendarRepository = calendarRepository;
        this.guardianRepository = guardianRepository;
    }

    public LiveData<List<Absence>> fetchAbsencesForChild(UUID childId) {
        return calendarRepository.fetchAbsencesForChild(childId);
    }

    public LiveData<Guardian> getGuardianLiveData(UUID guardianId) {
        return guardianRepository.getMutableLiveData(guardianId);
    }
}
