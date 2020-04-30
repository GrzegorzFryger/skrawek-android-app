package pl.edu.pjatk.pamo.skrawek.ui.absence;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.MyApplication;
import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.SharedViewModel;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.DayOffWork;
import pl.edu.pjatk.pamo.skrawek.ui.DaggerViewModelFactory;
import pl.edu.pjatk.pamo.skrawek.util.DateUtils;

public class AbsenceFragment extends Fragment implements OnDayClickListener {
    private static final String TAG = "AbsenceFragment";

    @Inject
    DaggerViewModelFactory viewModelFactory;

    @Inject
    DateUtils dateUtils;

    private DayOffWorkViewModel dayOffWorkViewModel;
    private SharedViewModel sharedViewModel;

    public static AbsenceFragment newInstance() {
        return new AbsenceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.absence_fragment, container, false);
        ((MyApplication) getActivity().getApplication()).getAppComponent().inject(this);
        dayOffWorkViewModel = new ViewModelProvider(getActivity(), viewModelFactory).get(DayOffWorkViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        markHolidaysInCalendar(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDayClick(EventDay eventDay) {
        Calendar clickedDayCalendar = eventDay.getCalendar();
        Log.i(TAG, "Clicked day: " + dateUtils.calendarToString(clickedDayCalendar));
    }

    private void markHolidaysInCalendar(View view) {
        List<DayOffWork> dayOfWorkList = dayOffWorkViewModel.getDaysOffWork().getValue();

        List<EventDay> events = new ArrayList<>();
        for (DayOffWork dayOffWork : dayOfWorkList) {
            events.add(dateUtils.prepareEventDay(dayOffWork));
        }

        CalendarView calendarView = view.findViewById(R.id.calendarView);
        calendarView.setEvents(events);
        calendarView.setOnDayClickListener(this);
    }
}
