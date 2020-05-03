package pl.edu.pjatk.pamo.skrawek.ui.absence;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.repository.CalendarRepository;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Child;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.Absence;

public class AbsenceViewModel extends ViewModel {
    private final CalendarRepository calendarRepository;

    private MutableLiveData<Child> selectedChild = new MutableLiveData<>();
    private LiveData<List<Absence>> listMutableLiveData = new MutableLiveData<>();

    @Inject
    public AbsenceViewModel(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;

        this.listMutableLiveData = Transformations.switchMap(
                selectedChild,
                s -> this.calendarRepository.fetchAbsencesForChild(s.getId())
        );
    }

    public LiveData<List<Absence>> fetchAbsencesForChild(UUID childId) {
        return calendarRepository.fetchAbsencesForChild(childId);
    }

    public void selectedChild(Child child) {
        this.selectedChild.setValue(child);
    }

    public LiveData<List<Absence>> getListMutableLiveData() {
        return listMutableLiveData;
    }
}
