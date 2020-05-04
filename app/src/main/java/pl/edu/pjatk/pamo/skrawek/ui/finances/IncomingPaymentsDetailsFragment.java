package pl.edu.pjatk.pamo.skrawek.ui.finances;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.SharedViewModel;
import pl.edu.pjatk.pamo.skrawek.databinding.IncomingPaymentsDetailsFragmentBinding;
import pl.edu.pjatk.pamo.skrawek.ui.DaggerViewModelFactory;

/**
 * This fragment is responsible for all details about incoming payments
 */
public class IncomingPaymentsDetailsFragment extends Fragment {

    private FinancesViewModel mViewModel;
    private SharedViewModel sharedViewModel;

    @Inject
    DaggerViewModelFactory viewModelFactory;
    private IncomingPaymentsDetailsFragmentBinding paymentDetailsBinding;

    public static IncomingPaymentsDetailsFragment newInstance() {
        return new IncomingPaymentsDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(FinancesViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        paymentDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.incoming_payments_details_fragment, container, false);
        paymentDetailsBinding.setVm(mViewModel);
        paymentDetailsBinding.setLifecycleOwner(this);

        return paymentDetailsBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
