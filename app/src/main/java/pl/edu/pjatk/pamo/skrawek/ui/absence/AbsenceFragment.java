package pl.edu.pjatk.pamo.skrawek.ui.absence;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.MyApplication;
import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.SharedViewModel;
import pl.edu.pjatk.pamo.skrawek.ui.DaggerViewModelFactory;

public class AbsenceFragment extends Fragment {

    @Inject
    DaggerViewModelFactory viewModelFactory;

    private DayOffWorkViewModel mViewModel;
    private SharedViewModel sharedViewModel;

    public static AbsenceFragment newInstance() {
        return new AbsenceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.absence_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MyApplication) getActivity().getApplication()).getAppComponent().inject(this);
        mViewModel = new ViewModelProvider(getActivity(), viewModelFactory).get(DayOffWorkViewModel.class);
        this.sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        // TODO: Use the ViewModel
    }

}
