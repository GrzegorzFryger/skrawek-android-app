package pl.edu.pjatk.pamo.skrawek.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.Absence;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.DayOffWork;
import pl.edu.pjatk.pamo.skrawek.rest.service.CalendarService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This repository is responsible for retrieving data from calendar module
 */
public class CalendarRepository {
    private static final String TAG = "CalendarRepository";

    private final CalendarService calendarService;
    private final MutableLiveData<List<DayOffWork>> daysOffWorkLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Absence>> absenceLiveData = new MutableLiveData<>();
    private final List<UUID> alreadyQuerriedChildren = new ArrayList<>();

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
                Log.i(TAG, "Downloaded list of holidays");
            }

            @Override
            public void onFailure(@NotNull Call<List<DayOffWork>> call, @NotNull Throwable t) {
                Log.e(TAG, "Failed to get list of days off work");
            }
        });
        return daysOffWorkLiveData;
    }

    public MutableLiveData<List<Absence>> getAbsenceLiveData() {
        return absenceLiveData;
    }

    public void fetchAbsencesForChild(UUID childId) {
        if (!alreadyQuerriedChildren.contains(childId)) {
            Call<List<Absence>> call = calendarService.getAllChildAbsences(childId);
            call.enqueue(new Callback<List<Absence>>() {
                @Override
                public void onResponse(@NotNull Call<List<Absence>> call, @NotNull Response<List<Absence>> response) {
                    List<Absence> absences = response.body();
                    if (absences != null) {
                        Objects.requireNonNull(absenceLiveData.getValue()).addAll(absences);
                    }
                    Log.i(TAG, "Downloaded list of absences for child: " + childId);
                }

                @Override
                public void onFailure(@NotNull Call<List<Absence>> call, @NotNull Throwable t) {
                    Log.e(TAG, "Failed to get list of days off work");
                }
            });
        }
    }

}
