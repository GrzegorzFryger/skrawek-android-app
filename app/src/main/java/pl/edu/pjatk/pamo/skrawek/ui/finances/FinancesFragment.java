package pl.edu.pjatk.pamo.skrawek.ui.finances;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.MyApplication;
import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.SharedViewModel;
import pl.edu.pjatk.pamo.skrawek.databinding.FinancesFragmentBinding;
import pl.edu.pjatk.pamo.skrawek.rest.model.finances.IncomingPayment;
import pl.edu.pjatk.pamo.skrawek.ui.DaggerViewModelFactory;

public class FinancesFragment extends Fragment {
    @Inject
    DaggerViewModelFactory viewModelFactory;

    private FinancesViewModel mViewModel;
    private SharedViewModel sharedViewModel;
    private RecyclerView recyclerView;

    private FinancesFragmentBinding financesFragmentBinding;

    public static FinancesFragment newInstance() {
        return new FinancesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.initializeViewModels();
        financesFragmentBinding = FinancesFragmentBinding.inflate(inflater, container, false);
        financesFragmentBinding.setVm(mViewModel);
        financesFragmentBinding.setLifecycleOwner(this);

        return financesFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sharedViewModel.getSelectedChild().observe(getViewLifecycleOwner(), s -> {
            this.mViewModel.selectChild(s);
        });

        mViewModel.getIncomingPayments().observe(getViewLifecycleOwner(), incomingPayments -> {
            this.recyclerView = this.financesFragmentBinding.listIncomingPayments;
            recyclerView.setLayoutManager(
                    new LinearLayoutManager(this.financesFragmentBinding.getRoot().getContext())
            );
            recyclerView.setAdapter(
                    new IncomingPaymentRecyclerViewAdapter(incomingPayments, onSelectPayment())
            );
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initializeViewModels() {
        ((MyApplication) getActivity().getApplication()).getAppComponent().inject(this);
        mViewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(FinancesViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(SharedViewModel.class);
    }

    private OnListFragmentInteractionListener onSelectPayment() {
        return item -> {
            mViewModel.selectIncomingPayment(item);
            Navigation.findNavController(this.financesFragmentBinding.getRoot())
                    .navigate(R.id.action_navigation_finances_to_incomingPaymentsDetailsFragment);
        };
    }

    public interface OnListFragmentInteractionListener {
        void onSelectPayment(IncomingPayment item);
    }
}
