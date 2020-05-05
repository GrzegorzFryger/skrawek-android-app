package pl.edu.pjatk.pamo.skrawek.rest.service;

import java.util.List;
import java.util.UUID;

import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.Absence;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.DayOffWork;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static pl.edu.pjatk.pamo.skrawek.rest.config.RequestMappings.API_CALENDAR;

/**
 * This service defines REST API urls for <strong>Calendar</strong> module (CalendarController)
 */
public interface CalendarService {


    /**
     * Prepare call rest Api to fetch {@link DayOffWork} data
     *
     * @return the all {@link DayOffWork}
     */
    @GET(API_CALENDAR + "daysoff")
    Call<List<DayOffWork>> getAllDaysOffWork();


    /**
     * Prepare call rest Api to fetch {@link Absence} data
     *
     * @param childId the child id
     * @return the all child absences
     */
    @GET(API_CALENDAR + "absence/childById/{childId}")
    Call<List<Absence>> getAllChildAbsences(@Path("childId") UUID childId);
}
