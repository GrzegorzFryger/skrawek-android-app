package pl.edu.pjatk.pamo.skrawek.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.MyApplication;
import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.databinding.AccountFragmentBinding;
import pl.edu.pjatk.pamo.skrawek.ui.DaggerViewModelFactory;

public class AccountFragment extends Fragment {

    private AccountViewModel mViewModel;
    @Inject
    DaggerViewModelFactory viewModelFactory;

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ((MyApplication) getActivity().getApplication()).getAppComponent().inject(this);
        mViewModel = new ViewModelProvider(getActivity(), viewModelFactory).get(AccountViewModel.class);

        AccountFragmentBinding accountFragmentBinding = DataBindingUtil
                .inflate(inflater, R.layout.account_fragment, container, false);
        accountFragmentBinding.setVm(mViewModel);
        accountFragmentBinding.setLifecycleOwner(this);

        return accountFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
