package pl.edu.pjatk.pamo.skrawek.ui.children;

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
import pl.edu.pjatk.pamo.skrawek.SharedViewModel;
import pl.edu.pjatk.pamo.skrawek.databinding.FragmentChildrenSelectBinding;
import pl.edu.pjatk.pamo.skrawek.ui.DaggerViewModelFactory;

public class ChildrenSelectFragment extends Fragment {
    private static final String TAG = "missiles";
    @Inject
    DaggerViewModelFactory viewModelFactory;

    private ChildrenSelectViewModel childrenSelectViewModel;
    private FragmentChildrenSelectBinding childrenSelectFragmentBinding;
    private SharedViewModel sharedViewModel;

    public static ChildrenSelectFragment newInstance() {
        return new ChildrenSelectFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        initializeViewModels();
        childrenSelectFragmentBinding = FragmentChildrenSelectBinding.inflate(inflater, container, false);
        childrenSelectFragmentBinding.setVm(childrenSelectViewModel);
        childrenSelectFragmentBinding.setLifecycleOwner(this);

        return childrenSelectFragmentBinding.getRoot();
    }

    private void initializeViewModels() {
        ((MyApplication) getActivity().getApplication()).getAppComponent().inject(this);
        childrenSelectViewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(ChildrenSelectViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(SharedViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sharedViewModel.getSelectedChild().observe(getViewLifecycleOwner(), child -> {
            this.childrenSelectViewModel.selectChild(child);
        });

        childrenSelectFragmentBinding.iconMenuButton.setOnClickListener(v -> openChildrenSelectDialog());
    }

    public void openChildrenSelectDialog() {
        ChildrenSelectDialogFragment newFragment = ChildrenSelectDialogFragment.newInstance();
        newFragment.setListener(item -> {
            this.childrenSelectViewModel.getSelectedChild().setValue(item);
            this.sharedViewModel.selectChild(item);
            newFragment.dismiss();
        });

        newFragment.show(getFragmentManager(), TAG);
    }

}
