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

import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.databinding.ChildrenSelectFragmentBinding;

public class ChildrenSelect extends Fragment {

    private ChildrenSelectViewModel mViewModel;
    private Button button;
    private ChildrenSelectFragmentBinding childrenSelectFragmentBinding;

    public static ChildrenSelect newInstance() {
        return new ChildrenSelect();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

       mViewModel = ViewModelProviders.of(this).get(ChildrenSelectViewModel.class);
        childrenSelectFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.children_select_fragment, container, false);
        childrenSelectFragmentBinding.setVm(mViewModel);
        childrenSelectFragmentBinding.setLifecycleOwner(this);
        return childrenSelectFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
