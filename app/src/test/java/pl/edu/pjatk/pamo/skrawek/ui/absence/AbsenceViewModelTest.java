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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import pl.edu.pjatk.pamo.skrawek.repository.CalendarRepository;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Child;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.Absence;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AbsenceViewModelTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private CalendarRepository calendarRepository;

    @Mock
    private Observer<List<Absence>> listObserver;

    private Absence absence;
    private MutableLiveData<List<Absence>> absenceLiveDataList;
    private AbsenceViewModel absenceViewModel;

    @Before
    public void setUp() {
        initMocks(this);

        Child child = new Child();
        child.setId(UUID.randomUUID());

        absence = new Absence();
        absence.setId(1L);
        absence.setReason("REASON");
        absence.setDate("2020-05-02");
        absence.setChildId(UUID.randomUUID());

        List<Absence> absences = new ArrayList<>();
        absences.add(absence);

        absenceLiveDataList = new MutableLiveData<>();
        absenceLiveDataList.setValue(absences);

        when(calendarRepository.fetchAbsencesForChild(any(UUID.class)))
                .thenReturn(absenceLiveDataList);
        absenceViewModel = new AbsenceViewModel(calendarRepository);
        absenceViewModel.selectedChild(child);
    }

    @Test
    public void Should_GetAbsenceMutableData() {
        //Given

        //When
        LiveData<List<Absence>> listLiveData = absenceViewModel.getListMutableLiveData();
        listLiveData.observeForever(listObserver);
        List<Absence> absences = listLiveData.getValue();

        //Then
        assertNotNull(absences);
        assertEquals(1, absences.size());
        assertEquals(absence.getChildId(), absences.get(0).getChildId());
        assertEquals(absence.getDate(), absences.get(0).getDate());
        assertEquals(absence.getId(), absences.get(0).getId());
        assertEquals(absence.getReason(), absences.get(0).getReason());
    }

    @Test
    public void Should_FetchAbsencesForChild() {
        //Given

        //When
        LiveData<List<Absence>> listLiveData = absenceViewModel.fetchAbsencesForChild(UUID.randomUUID());
        listLiveData.observeForever(listObserver);
        List<Absence> absences = listLiveData.getValue();

        //Then
        assertNotNull(absences);
        assertEquals(1, absences.size());
    }
}