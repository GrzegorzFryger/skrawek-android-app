package pl.edu.pjatk.pamo.skrawek.ui.absence;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.repository.CalendarRepository;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.DayOffWork;

public class DayOffWorkViewModel extends ViewModel {
    private final CalendarRepository calendarRepository;
    private MutableLiveData<List<DayOffWork>> daysOffWorkLiveData = new MutableLiveData<>();

    @Inject
    public DayOffWorkViewModel(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    public void initializeData() {
        daysOffWorkLiveData = calendarRepository.getMutableLiveData();
    }

    public LiveData<List<DayOffWork>> getDaysOffWork() {
        return daysOffWorkLiveData;
    }
}
