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

import java.util.Comparator;
import java.util.stream.Collectors;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.MyApplication;
import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.SharedViewModel;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Child;
import pl.edu.pjatk.pamo.skrawek.ui.DaggerViewModelFactory;

public class ChildrenSelectDialog extends DialogFragment {

    @Inject
    DaggerViewModelFactory viewModelFactory;
    private View view;
    private RecyclerView recyclerView;
    private OnSelectChildrenFromList listener;
    private SharedViewModel sharedViewModel;

    public static ChildrenSelectDialog newInstance() {
        return new ChildrenSelectDialog();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.children_select_dialog_fragment, container, false);
        ((MyApplication) getActivity().getApplication()).getAppComponent().inject(this);
        sharedViewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(SharedViewModel.class);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sharedViewModel.getLoggedGuardian().observe(this, guardian -> {
            this.recyclerView = view.findViewById(R.id.list);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.setAdapter(
                    new ChildrenRecyclerViewAdapter(
                            guardian.getChildren()
                                    .stream()
                                    .sorted(Comparator.comparing(Child::getName))
                                    .collect(Collectors.toList()),
                            listener
                    ));
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public void setListener(OnSelectChildrenFromList listener) {
        this.listener = listener;
    }


    public interface OnSelectChildrenFromList {
        void onSelectedChild(Child item);
    }

}
