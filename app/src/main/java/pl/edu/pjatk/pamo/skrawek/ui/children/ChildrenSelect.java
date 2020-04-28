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

import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.SharedViewModel;
import pl.edu.pjatk.pamo.skrawek.databinding.ChildrenSelectFragmentBinding;

import static pl.edu.pjatk.pamo.skrawek.rest.auth.SessionManager.getGuardianId;

public class ChildrenSelect extends Fragment {
    private String loggedUserId;
    private ChildrenSelectViewModel childrenSelectViewModel;
    private ChildrenSelectFragmentBinding childrenSelectFragmentBinding;
    private SharedViewModel sharedViewModel;

    public static ChildrenSelect newInstance() {
        return new ChildrenSelect();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        loggedUserId = getGuardianId().toString();
        childrenSelectViewModel = new ViewModelProvider(this).get(ChildrenSelectViewModel.class);
        childrenSelectFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.children_select_fragment, container, false);
        childrenSelectFragmentBinding.setVm(childrenSelectViewModel);
        childrenSelectFragmentBinding.setLifecycleOwner(this);
        childrenSelectFragmentBinding.iconMenuButton.setOnClickListener(v -> openChildrenSelectDialog());
        return childrenSelectFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    public void openChildrenSelectDialog() {
        ChildrenSelectDialog newFragment = ChildrenSelectDialog.newInstance(this.loggedUserId);
        newFragment.setListener(item -> {
            this.childrenSelectViewModel.getSelectedChild().setValue(item);
            this.sharedViewModel.selectChild(item);
            newFragment.dismiss();
        });
        newFragment.show(getFragmentManager(), "missiles");
    }

}
