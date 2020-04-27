package pl.edu.pjatk.pamo.skrawek.ui.children;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.UUID;

import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Child;

public class ChildrenSelectDialog extends DialogFragment {

    private static final String PARAM_GUARDIAN_ID = "GUARDIAN_ID";
    private String guardianIdParam;

    private RecyclerView recyclerView;
    private OnSelectChildrenFromList listener;
    private ChildrenSelectDialogViewModel mViewModel;

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
        mViewModel = ViewModelProviders.of(this).get(ChildrenSelectDialogViewModel.class);
        View view = inflater.inflate(R.layout.children_select_dialog_fragment, container, false);


        mViewModel.getGuardianLiveData().observe(this, guardian -> {
            this.recyclerView = view.findViewById(R.id.list);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.setAdapter(new ChildrenRecyclerViewAdapter(guardian.getChildren(), listener));
        });

        mViewModel.getGuardianIdPublisher().setValue(UUID.fromString(guardianIdParam));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
