package pl.edu.pjatk.pamo.skrawek.ui.absence;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.MyApplication;
import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.SharedViewModel;
import pl.edu.pjatk.pamo.skrawek.ui.DaggerViewModelFactory;

/**
 * This fragment is responsible for showing absences panel
 */
public class AbsenceFragment extends Fragment {
    @Inject
    DaggerViewModelFactory viewModelFactory;

    private AbsenceViewModel mViewModel;
    private SharedViewModel sharedViewModel;
    private RecyclerView recyclerView;

    public static AbsenceFragment newInstance() {
        return new AbsenceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        initializeViewModels();
        View view = inflater.inflate(R.layout.fragment_absence, container, false);
        recyclerView = view.findViewById(R.id.listAbsence);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sharedViewModel.getSelectedChild().observe(getViewLifecycleOwner(), child -> {
            this.mViewModel.selectedChild(child);
        });

        mViewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), absences -> {
            recyclerView.setAdapter(new AbsenceRecyclerViewAdapter(absences));
        });
    }

    private void initializeViewModels() {
        ((MyApplication) getActivity().getApplication()).getAppComponent().inject(this);
        mViewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(AbsenceViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(SharedViewModel.class);
    }

}
