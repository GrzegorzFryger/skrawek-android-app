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

import java.util.ArrayList;

import pl.edu.pjatk.pamo.skrawek.R;

public class ChildrenSelectDialog extends DialogFragment {

    private static final String CHILDREN_PARAM = "param1";
    private ArrayList<String> children;
    private RecyclerView recyclerView;
    private OnSelectChildrenFromList listener;

    public static ChildrenSelectDialog newInstance(ArrayList<String> children) {
        ChildrenSelectDialog childrenSelectDialog = new ChildrenSelectDialog();
        Bundle args = new Bundle();
        args.putSerializable(CHILDREN_PARAM, children);
        childrenSelectDialog.setArguments(args);
        return childrenSelectDialog;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.children_select_dialog_fragment, container, false);


        this.recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new ChildrenRecyclerViewAdapter(children, listener));

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
        this.children = (ArrayList<String>) getArguments().getSerializable(CHILDREN_PARAM);
    }

    public interface OnSelectChildrenFromList {
        void onSelectedChild(String item);
    }

}
