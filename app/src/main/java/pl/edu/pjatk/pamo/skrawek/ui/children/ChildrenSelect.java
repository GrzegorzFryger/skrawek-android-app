package pl.edu.pjatk.pamo.skrawek.ui.children;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.MyApplication;
import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.SharedViewModel;
import pl.edu.pjatk.pamo.skrawek.databinding.ChildrenSelectFragmentBinding;
import pl.edu.pjatk.pamo.skrawek.ui.DaggerViewModelFactory;

public class ChildrenSelect extends Fragment {
    @Inject
    DaggerViewModelFactory viewModelFactory;

    private ChildrenSelectViewModel childrenSelectViewModel;
    private ChildrenSelectFragmentBinding childrenSelectFragmentBinding;
    private SharedViewModel sharedViewModel;

    public static ChildrenSelect newInstance() {
        return new ChildrenSelect();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ((MyApplication) getActivity().getApplication()).getAppComponent().inject(this);
        childrenSelectViewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(ChildrenSelectViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(SharedViewModel.class);

        childrenSelectFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.children_select_fragment, container, false);
        childrenSelectFragmentBinding.setVm(childrenSelectViewModel);
        childrenSelectFragmentBinding.setLifecycleOwner(this);

        return childrenSelectFragmentBinding.getRoot();
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
        ChildrenSelectDialog newFragment = ChildrenSelectDialog.newInstance();
        newFragment.setListener(item -> {
            this.childrenSelectViewModel.getSelectedChild().setValue(item);
            this.sharedViewModel.selectChild(item);
            newFragment.dismiss();
        });
        newFragment.show(getFragmentManager(), "missiles");
    }

}
