package pl.edu.pjatk.pamo.skrawek.ui.finances;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.MyApplication;
import pl.edu.pjatk.pamo.skrawek.databinding.FragmentIncomingPaymentsDetailsBinding;
import pl.edu.pjatk.pamo.skrawek.ui.DaggerViewModelFactory;

public class IncomingPaymentsDetailsFragment extends Fragment {
    @Inject
    DaggerViewModelFactory viewModelFactory;

    private FinancesViewModel financesViewModel;
    private FragmentIncomingPaymentsDetailsBinding paymentDetailsBinding;

    public static IncomingPaymentsDetailsFragment newInstance() {
        return new IncomingPaymentsDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        initializeViewModels();
        paymentDetailsBinding = FragmentIncomingPaymentsDetailsBinding.inflate(inflater, container, false);
        paymentDetailsBinding.setFinancesViewModel(financesViewModel);
        paymentDetailsBinding.setLifecycleOwner(this);

        return paymentDetailsBinding.getRoot();
    }

    private void initializeViewModels() {
        ((MyApplication) getActivity().getApplication()).getAppComponent().inject(this);
        financesViewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(FinancesViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
