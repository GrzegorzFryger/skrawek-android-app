package pl.edu.pjatk.pamo.skrawek.repository;

import androidx.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.UUID;

import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.Absence;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.DayOffWork;
import pl.edu.pjatk.pamo.skrawek.rest.service.CalendarService;
import retrofit2.Call;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CalendarRepositoryTest {
    @Mock
    private CalendarService calendarService;

    private CalendarRepository calendarRepository;

    @Before
    public void setUp() {
        initMocks(this);
        calendarRepository = new CalendarRepository(calendarService);
    }

    @Test
    public void Should_GetMutableLiveDataFor_DaysOffWork() {
        //Given
        Call call = mock(Call.class);

        //When
        when(calendarService.getAllDaysOffWork()).thenReturn(call);
        MutableLiveData<List<DayOffWork>> result = calendarRepository.getMutableLiveData();

        //Then
        assertNotNull(result);
    }

    @Test
    public void Should_GetMutableLiveDataFor_Absences() {
        //Given
        Call call = mock(Call.class);

        //When
        when(calendarService.getAllChildAbsences(any(UUID.class))).thenReturn(call);
        MutableLiveData<List<Absence>> result =
                calendarRepository.fetchAbsencesForChild(UUID.randomUUID());

        //Then
        assertNotNull(result);
    }
}