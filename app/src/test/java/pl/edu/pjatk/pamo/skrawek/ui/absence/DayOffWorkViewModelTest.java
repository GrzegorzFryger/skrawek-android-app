package pl.edu.pjatk.pamo.skrawek.ui.absence;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import pl.edu.pjatk.pamo.skrawek.repository.CalendarRepository;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.DayOffWork;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.EventType;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DayOffWorkViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private CalendarRepository calendarRepository;

    @Mock
    private Observer<List<DayOffWork>> daysOfWorkObserver;

    private MutableLiveData<List<DayOffWork>> data;

    private DayOffWorkViewModel dayOffWorkViewModel;

    @Before
    public void setUp() {
        initMocks(this);

        dayOffWorkViewModel = new DayOffWorkViewModel(calendarRepository);


        DayOffWork dayOffWork = new DayOffWork();
        dayOffWork.setDate("2020-01-01");
        dayOffWork.setEventType(EventType.HOLIDAY);
        dayOffWork.setId(1L);
        dayOffWork.setName("Some holiday");

        List<DayOffWork> dayOffWorkList = new ArrayList<>();
        dayOffWorkList.add(dayOffWork);

        data = new MutableLiveData<>();
        data.setValue(dayOffWorkList);

        when(calendarRepository.getMutableLiveData()).thenReturn(data);
    }

    @Test
    public void Should_GetListOfDaysOffWork_From_DayOffWorkMutableLiveData() {
        //Given

        //When
        dayOffWorkViewModel.initializeData();
        LiveData<List<DayOffWork>> daysOffWork = dayOffWorkViewModel.getDaysOffWork();
        DayOffWork result = daysOffWork.getValue().get(0);
        daysOffWork.observeForever(daysOfWorkObserver);

        //Then
        verify(calendarRepository, only()).getMutableLiveData();
        assertEquals(1, daysOffWork.getValue().size());

        assertEquals(new Long(1), result.getId());
        assertEquals("2020-01-01", result.getDate());
        assertEquals(EventType.HOLIDAY, result.getEventType());
        assertEquals("Some holiday", result.getName());
    }

}