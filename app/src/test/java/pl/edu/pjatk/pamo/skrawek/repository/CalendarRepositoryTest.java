package pl.edu.pjatk.pamo.skrawek.repository;

import androidx.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.DayOffWork;
import pl.edu.pjatk.pamo.skrawek.rest.service.CalendarService;
import retrofit2.Call;

import static org.junit.Assert.assertNotNull;
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
}