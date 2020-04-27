package pl.edu.pjatk.pamo.skrawek.ui.finances;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.SharedViewModel;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Child;

public class FinancesFragment extends Fragment {

    private FinancesViewModel mViewModel;
    private SharedViewModel sharedViewModel;
    TextView textureView;

    public static FinancesFragment newInstance() {
        return new FinancesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.finances_fragment, container, false);
        textureView  = view.findViewById(R.id.test);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FinancesViewModel.class);
        this.sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getSelectedChild().observe(getViewLifecycleOwner(), s -> {
            Child child = s;
            textureView.setText(s.getName());
        });

        System.out.println(sharedViewModel.getSelectedChild().getValue());
        // TODO: Use the ViewModel
    }

}
