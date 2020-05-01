package pl.edu.pjatk.pamo.skrawek.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.DayOffWork;
import pl.edu.pjatk.pamo.skrawek.ui.DaggerViewModelFactory;
import pl.edu.pjatk.pamo.skrawek.ui.absence.AbsenceEventDay;
import pl.edu.pjatk.pamo.skrawek.ui.absence.DayOffWorkViewModel;
import pl.edu.pjatk.pamo.skrawek.util.DateUtils;

public class HomeFragment extends Fragment implements OnDayClickListener {
    private static final String TAG = "HomeFragment";

    @Inject
    DaggerViewModelFactory viewModelFactory;

    @Inject
    DateUtils dateUtils;

    private DayOffWorkViewModel dayOffWorkViewModel;
    private TextView absenceTextView;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ((MyApplication) getActivity().getApplication()).getAppComponent().inject(this);
        dayOffWorkViewModel = new ViewModelProvider(getActivity(), viewModelFactory).get(DayOffWorkViewModel.class);
        absenceTextView = view.findViewById(R.id.absenceTextView);
        markHolidaysInCalendar(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDayClick(EventDay eventDay) {
        if (eventDay instanceof AbsenceEventDay) {
            AbsenceEventDay absenceEventDay = (AbsenceEventDay) eventDay;
            Calendar clickedDayCalendar = eventDay.getCalendar();
            Log.i(TAG, "Clicked day: " + dateUtils.calendarToString(clickedDayCalendar));
            absenceTextView.setText(absenceEventDay.getEventDescription());
        } else {
            absenceTextView.setText("");
        }
    }

    private void markHolidaysInCalendar(View view) {
        dayOffWorkViewModel.getDaysOffWork().observe(this.getViewLifecycleOwner(),
                dayOffWorks -> {
                    List<EventDay> events = new ArrayList<>();
                    for (DayOffWork dayOffWork : dayOffWorks) {
                        events.add(dateUtils.prepareEventDay(dayOffWork));
                    }
                    CalendarView calendarView = view.findViewById(R.id.calendarView);
                    calendarView.setEvents(events);
                    calendarView.setOnDayClickListener(this);
                });
    }
}