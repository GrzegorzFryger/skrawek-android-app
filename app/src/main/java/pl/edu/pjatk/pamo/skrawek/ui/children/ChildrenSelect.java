package pl.edu.pjatk.pamo.skrawek.ui.children;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.UUID;

import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.databinding.ChildrenSelectFragmentBinding;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Child;

public class ChildrenSelect extends Fragment  {
    private String loggedUserId = "84187cf8-6547-49c0-be3d-00e9137b86bd";
    private ChildrenSelectViewModel mViewModel;
    private ChildrenSelectFragmentBinding childrenSelectFragmentBinding;

    public static ChildrenSelect newInstance() {
        return new ChildrenSelect();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(ChildrenSelectViewModel.class);
        childrenSelectFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.children_select_fragment, container, false);
        childrenSelectFragmentBinding.setVm(mViewModel);
        childrenSelectFragmentBinding.setLifecycleOwner(this);


        childrenSelectFragmentBinding.iconMenuButton.setOnClickListener(v -> openChildrenSelectDialog());
        return childrenSelectFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void openChildrenSelectDialog() {
        ChildrenSelectDialog newFragment = ChildrenSelectDialog.newInstance(this.loggedUserId);
        newFragment.setListener(item -> {
            this.mViewModel.getSelectedChild().setValue(item);
            newFragment.dismiss();
        });
        newFragment.show(getFragmentManager(), "missiles");
    }

}
