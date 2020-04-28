package pl.edu.pjatk.pamo.skrawek.ui.children;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.UUID;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.MyApplication;
import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Child;
import pl.edu.pjatk.pamo.skrawek.ui.DaggerViewModelFactory;

public class ChildrenSelectDialog extends DialogFragment {

    private static final String PARAM_GUARDIAN_ID = "GUARDIAN_ID";
    private String guardianIdParam;
    private View view;
    private RecyclerView recyclerView;
    private OnSelectChildrenFromList listener;

    @Inject
    DaggerViewModelFactory viewModelFactory;
    ChildrenSelectDialogViewModel mViewModel;

    public static ChildrenSelectDialog newInstance(String guardianIdParam) {
        ChildrenSelectDialog childrenSelectDialog = new ChildrenSelectDialog();
        Bundle args = new Bundle();
        args.putSerializable(PARAM_GUARDIAN_ID, guardianIdParam);
        childrenSelectDialog.setArguments(args);
        return childrenSelectDialog;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.children_select_dialog_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MyApplication) getActivity().getApplication()).getAppComponent().inject(this);
        mViewModel = new ViewModelProvider(this, viewModelFactory).get(ChildrenSelectDialogViewModel.class);

        mViewModel.getGuardianLiveData().observe(this, guardian -> {
            this.recyclerView = view.findViewById(R.id.list);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.setAdapter(new ChildrenRecyclerViewAdapter(guardian.getChildren(), listener));
        });

        mViewModel.getGuardianIdPublisher().setValue(UUID.fromString(guardianIdParam));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public void setListener(OnSelectChildrenFromList listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            assignParameterFromBundleContext();
        }
    }

    private void assignParameterFromBundleContext() {
        this.guardianIdParam = getArguments().getString(PARAM_GUARDIAN_ID);
    }

    public interface OnSelectChildrenFromList {
        void onSelectedChild(Child item);
    }

}
