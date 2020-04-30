package pl.edu.pjatk.pamo.skrawek.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.DayOffWork;
import pl.edu.pjatk.pamo.skrawek.rest.service.CalendarService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This repository is responsible for retrieving days calendar data
 */
public class CalendarRepository {
    private static final String TAG = "CalendarRepository";

    private final CalendarService calendarService;
    private final MutableLiveData<List<DayOffWork>> daysOffWorkLiveData = new MutableLiveData<>();

    @Inject
    public CalendarRepository(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    public MutableLiveData<List<DayOffWork>> getMutableLiveData() {
        Call<List<DayOffWork>> call = calendarService.getAllDaysOffWork();
        call.enqueue(new Callback<List<DayOffWork>>() {
            @Override
            public void onResponse(@NotNull Call<List<DayOffWork>> call, @NotNull Response<List<DayOffWork>> response) {
                List<DayOffWork> dayOffWorkList = response.body();
                if (dayOffWorkList != null) {
                    daysOffWorkLiveData.setValue(dayOffWorkList);
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<DayOffWork>> call, @NotNull Throwable t) {
                Log.e(TAG, "Failed to get list of days off work");
            }
        });
        return daysOffWorkLiveData;
    }
}
