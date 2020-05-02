package pl.edu.pjatk.pamo.skrawek.ui.absence;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.repository.CalendarRepository;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.Absence;

public class AbsenceViewModel extends ViewModel {
    private final CalendarRepository calendarRepository;

    @Inject
    public AbsenceViewModel(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    public LiveData<List<Absence>> fetchAbsencesForChild(UUID childId) {
        return calendarRepository.fetchAbsencesForChild(childId);
    }
}
