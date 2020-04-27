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

public class ChildrenSelect extends Fragment  {
    private String loggedUserId = "84187cf8-6547-49c0-be3d-00e9137b86bd";
    private ChildrenSelectViewModel childrenSelectViewModel;
    private ChildrenSelectFragmentBinding childrenSelectFragmentBinding;
    private SharedViewModel sharedViewModel;

    public static ChildrenSelect newInstance() {
        return new ChildrenSelect();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
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
            System.out.println("Select children childselect");
            this.sharedViewModel.selectChild(item);
            newFragment.dismiss();
        });
        newFragment.show(getFragmentManager(), "missiles");
    }

}
