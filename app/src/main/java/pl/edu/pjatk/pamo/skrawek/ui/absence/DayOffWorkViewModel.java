package pl.edu.pjatk.pamo.skrawek.ui.absence;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.repository.CalendarRepository;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.DayOffWork;


/**
 * The type {@link ViewModel} class responsible to store and manage {@link DayOffWork} UI-related data.
 */
public class DayOffWorkViewModel extends ViewModel {
    private final CalendarRepository calendarRepository;
    private MutableLiveData<List<DayOffWork>> daysOffWorkLiveData = new MutableLiveData<>();

    /**
     * Instantiates a new Day off work view model.
     *
     * @param calendarRepository the calendar repository
     */
    @Inject
    public DayOffWorkViewModel(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    /**
     * Initialize data.
     */
    public void initializeData() {
        daysOffWorkLiveData = calendarRepository.getListDayOff();
    }

    /**
     * Gets days off work.
     *
     * @return the days off work
     */
    public LiveData<List<DayOffWork>> getDaysOffWork() {
        return daysOffWorkLiveData;
    }
}
