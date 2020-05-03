package pl.edu.pjatk.pamo.skrawek.util;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.Absence;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.DayOffWork;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.EventType;
import pl.edu.pjatk.pamo.skrawek.ui.absence.AbsenceEventDay;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DateUtilsTest {

    private DateUtils dateUtils;

    @Before
    public void setUp() {
        dateUtils = new DateUtils();
    }

    @Test
    public void Should_ConvertLocalDate_To_Calendar() {
        //Given
        LocalDate input = LocalDate.of(2020, 1, 1);

        //When
        Calendar result = dateUtils.toCalendar(input);

        //Then
        assertNotNull(result);
        assertEquals(input.getYear(), result.get(Calendar.YEAR));
        // In Calendar month starts from 0 instead of 1, so we add +1
        assertEquals(input.getMonth().getValue(), result.get(Calendar.MONTH) + 1);
        assertEquals(input.getDayOfMonth(), result.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void Should_ConvertLocalDate_To_Date() {
        //Given
        LocalDate input = LocalDate.of(2020, 1, 1);

        //When
        Date result = dateUtils.toDate(input);

        //Then
        assertNotNull(result);
    }

    @Test
    public void Should_ConvertCalendar_ToString() {
        //Given
        String expected = "2020-01-01";
        LocalDate localDate = LocalDate.of(2020, 1, 1);
        Calendar input = dateUtils.toCalendar(localDate);

        //When
        String result = dateUtils.calendarToString(input);

        //Then
        assertEquals(expected, result);
    }

    @Test
    public void Should_CreateEventDay() {
        //Given
        DayOffWork input = buildDayOffWork(null);

        //When
        AbsenceEventDay result = dateUtils.prepareEventDay(input);

        //Then
        assertNotNull(result);
        assertEquals("Some name", result.getEventDescription());
    }

    @Test
    public void Should_CreateHolidayEventDay() {
        //Given
        DayOffWork input = buildDayOffWork(EventType.HOLIDAY);

        //When
        AbsenceEventDay result = dateUtils.prepareEventDay(input);

        //Then
        assertNotNull(result);
        assertEquals(R.drawable.holiday_24, result.getImageDrawable());
        assertEquals("Some name", result.getEventDescription());
    }

    @Test
    public void Should_CreateInternalEventDay() {
        //Given
        DayOffWork input = buildDayOffWork(EventType.INTERNAL_EVENT);

        //When
        AbsenceEventDay result = dateUtils.prepareEventDay(input);

        //Then
        assertNotNull(result);
        assertEquals(R.drawable.outline_school_24, result.getImageDrawable());
        assertEquals("Some name", result.getEventDescription());
    }

    @Test
    public void Should_CreateWeekendDay() {
        //Given
        DayOffWork input = buildDayOffWork(EventType.WEEKEND);

        //When
        AbsenceEventDay result = dateUtils.prepareEventDay(input);

        //Then
        assertNotNull(result);
        assertEquals("Some name", result.getEventDescription());
    }

    @Test
    public void Should_CreateAbsenceEventDay() {
        //Given
        Absence absence = new Absence();
        absence.setChildId(UUID.randomUUID());
        absence.setDate("2020-05-20");
        absence.setReason("Choroba");

        //When
        AbsenceEventDay result = dateUtils.prepareEventDay(absence, "Choroba");

        //Then
        assertNotNull(result);
        assertEquals(R.drawable.child_face_24, result.getImageDrawable());
        assertTrue(result.getEventDescription().contains("Choroba"));
    }

    private DayOffWork buildDayOffWork(EventType eventType) {
        DayOffWork dayOffWork = new DayOffWork();
        dayOffWork.setName("Some name");
        dayOffWork.setId(1L);
        dayOffWork.setEventType(eventType);
        dayOffWork.setDate("2020-01-01");
        return dayOffWork;
    }

}