package pl.edu.pjatk.pamo.skrawek.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.MyApplication;
import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.SharedViewModel;
import pl.edu.pjatk.pamo.skrawek.databinding.FragmentHomeBinding;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Child;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.Absence;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.DayOffWork;
import pl.edu.pjatk.pamo.skrawek.ui.DaggerViewModelFactory;
import pl.edu.pjatk.pamo.skrawek.ui.absence.AbsenceEventDay;
import pl.edu.pjatk.pamo.skrawek.ui.absence.AbsenceViewModel;
import pl.edu.pjatk.pamo.skrawek.ui.absence.DayOffWorkViewModel;
import pl.edu.pjatk.pamo.skrawek.util.DateUtils;

import static pl.edu.pjatk.pamo.skrawek.MyApplication.getStringFromRes;

public class HomeFragment extends Fragment implements OnDayClickListener {
    private static final String TAG = "HomeFragment";
    private String NAME_SURNAME_TEMPLATE = "%s  %s";

    @Inject
    DaggerViewModelFactory viewModelFactory;

    @Inject
    DateUtils dateUtils;

    private DayOffWorkViewModel dayOffWorkViewModel;
    private AbsenceViewModel absenceViewModel;
    private List<EventDay> events = new ArrayList<>();
    private SharedViewModel sharedViewModel;
    private FragmentHomeBinding homeFragmentBinding;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.initializeViewModels();
        homeFragmentBinding = FragmentHomeBinding.inflate(inflater, container, false);
        homeFragmentBinding.setLifecycleOwner(this);

        return homeFragmentBinding.getRoot();
    }

    private void initializeViewModels() {
        ((MyApplication) getActivity().getApplication()).getAppComponent().inject(this);
        dayOffWorkViewModel = new ViewModelProvider(getActivity(), viewModelFactory).get(DayOffWorkViewModel.class);
        absenceViewModel = new ViewModelProvider(getActivity(), viewModelFactory).get(AbsenceViewModel.class);
        sharedViewModel = new ViewModelProvider(getActivity(), viewModelFactory).get(SharedViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.homeFragmentBinding.profileImageHome.setOnClickListener(image ->
                Navigation.findNavController(this.homeFragmentBinding.getRoot())
                        .navigate(R.id.action_navigation_home_to_navigation_account)
        );

        sharedViewModel.getLoggedGuardian().observe(getViewLifecycleOwner(), guardian -> {
            this.addChildAbsences(
                    guardian.getChildren()
                            .stream()
                            .map(Child::getId).collect(Collectors.toList())
            );

            if (isWoman(guardian.getName())) {
                this.homeFragmentBinding.profileImageHome.setImageResource(R.drawable.img_mom);
            } else {
                this.homeFragmentBinding.profileImageHome.setImageResource(R.drawable.img_dad);
            }

            this.homeFragmentBinding.nameSurnameHome.setText(
                    String.format(
                            NAME_SURNAME_TEMPLATE,
                            guardian.getName(), guardian.getSurname()
                    )
            );
        });

        this.homeFragmentBinding.calendarView.setOnDayClickListener(this);
        markHolidaysInCalendar();
    }

    @Override
    public void onDayClick(EventDay eventDay) {
        if (eventDay instanceof AbsenceEventDay) {
            AbsenceEventDay absenceEventDay = (AbsenceEventDay) eventDay;
            Calendar clickedDayCalendar = eventDay.getCalendar();
            Log.i(TAG, "Clicked day: " + dateUtils.calendarToString(clickedDayCalendar));
            this.homeFragmentBinding.eventInfo.setText(absenceEventDay.getEventDescription());
        } else {
            this.homeFragmentBinding.eventInfo.setText(R.string.none);
        }
    }

    private void markHolidaysInCalendar() {
        dayOffWorkViewModel.getDaysOffWork().observe(this.getViewLifecycleOwner(),
                dayOffWorks -> {
                    for (DayOffWork dayOffWork : dayOffWorks) {
                        events.add(dateUtils.prepareEventDay(dayOffWork));
                    }
                    this.homeFragmentBinding.calendarView.setEvents(events);
                });
    }

    private void addChildAbsences(List<UUID> childIdList) {
        for (UUID childId : childIdList) {
            absenceViewModel.fetchAbsencesForChild(childId).observe(
                    this.getViewLifecycleOwner(),
                    absences -> {
                        for (Absence absence : absences) {
                            events.add(dateUtils.prepareEventDay(absence,
                                    getStringFromRes(R.string.child_absence)));
                        }
                        this.homeFragmentBinding.calendarView.setEvents(events);
                    }
            );
        }
    }

    private boolean isWoman(String name) {
        return name.substring(name.length() - 1).toLowerCase().trim().equals("a");
    }

}