package pl.edu.pjatk.pamo.skrawek.ui.children;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.edu.pjatk.pamo.skrawek.R;

public class ChildernSelectDialog extends DialogFragment {

    private ChildernSelectDialogViewModel mViewModel;

    public static ChildernSelectDialog newInstance() {
        return new ChildernSelectDialog();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.childern_select_dialog_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ChildernSelectDialogViewModel.class);
        // TODO: Use the ViewModel
    }

}
