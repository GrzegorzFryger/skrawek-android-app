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
import androidx.navigation.Navigation;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.MyApplication;
import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.Absence;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.DayOffWork;
import pl.edu.pjatk.pamo.skrawek.ui.DaggerViewModelFactory;
import pl.edu.pjatk.pamo.skrawek.ui.absence.AbsenceEventDay;
import pl.edu.pjatk.pamo.skrawek.ui.absence.AbsenceViewModel;
import pl.edu.pjatk.pamo.skrawek.ui.absence.DayOffWorkViewModel;
import pl.edu.pjatk.pamo.skrawek.util.DateUtils;

import static pl.edu.pjatk.pamo.skrawek.rest.auth.SessionManager.getGuardianId;

public class HomeFragment extends Fragment implements OnDayClickListener {
    private static final String TAG = "HomeFragment";

    @Inject
    DaggerViewModelFactory viewModelFactory;

    @Inject
    DateUtils dateUtils;

    private DayOffWorkViewModel dayOffWorkViewModel;
    private AbsenceViewModel absenceViewModel;
    private TextView absenceTextView;
    private CalendarView calendarView;
    private List<EventDay> events;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ((MyApplication) getActivity().getApplication()).getAppComponent().inject(this);
        dayOffWorkViewModel = new ViewModelProvider(getActivity(), viewModelFactory).get(DayOffWorkViewModel.class);
        absenceViewModel = new ViewModelProvider(getActivity(), viewModelFactory).get(AbsenceViewModel.class);

        view.findViewById(R.id.profile_image).setOnClickListener(c -> {
            Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_navigation_account);
        });

        events = new ArrayList<>();
        absenceTextView = view.findViewById(R.id.event_info);
        calendarView = view.findViewById(R.id.calendarView);
        calendarView.setOnDayClickListener(this);
        markHolidaysInCalendar();
        addChildAbsences();
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
            absenceTextView.setText(R.string.none);
        }
    }

    private void markHolidaysInCalendar() {
        dayOffWorkViewModel.getDaysOffWork().observe(this.getViewLifecycleOwner(),
                dayOffWorks -> {
                    for (DayOffWork dayOffWork : dayOffWorks) {
                        events.add(dateUtils.prepareEventDay(dayOffWork));
                    }
                    calendarView.setEvents(events);
                });
    }

    private void addChildAbsences() {
        absenceViewModel.getGuardianLiveData(getGuardianId()).observe(this.getViewLifecycleOwner(),
                guardian -> {
                    guardian.getChildren().forEach(child -> {
                        Log.i(TAG, "Loading absences for child: " + child.getId());
                        absenceViewModel.fetchAbsencesForChild(child.getId()).observe(
                                this.getViewLifecycleOwner(), absences -> {
                                    for (Absence absence : absences) {
                                        Log.i(TAG, "Adding absence for " + child.getId());
                                        events.add(dateUtils.prepareEventDay(absence, child.getName()));
                                    }
                                    calendarView.setEvents(events);
                                }
                        );
                    });
                });
    }

}