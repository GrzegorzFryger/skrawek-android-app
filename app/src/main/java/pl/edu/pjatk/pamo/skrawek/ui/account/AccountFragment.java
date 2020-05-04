package pl.edu.pjatk.pamo.skrawek.ui.account;

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
import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.SharedViewModel;
import pl.edu.pjatk.pamo.skrawek.databinding.FragmentAccountBinding;
import pl.edu.pjatk.pamo.skrawek.ui.DaggerViewModelFactory;

public class AccountFragment extends Fragment {

    private AccountViewModel mViewModel;
    @Inject
    DaggerViewModelFactory viewModelFactory;
    private SharedViewModel sharedViewModel;
    private FragmentAccountBinding accountFragmentBinding;

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        initializeViewModels();
        accountFragmentBinding = FragmentAccountBinding.inflate(inflater, container, false);
        accountFragmentBinding.setVm(mViewModel);
        accountFragmentBinding.setLifecycleOwner(this);

        return accountFragmentBinding.getRoot();
    }

    private void initializeViewModels() {
        ((MyApplication) getActivity().getApplication()).getAppComponent().inject(this);
        mViewModel = new ViewModelProvider(getActivity(), viewModelFactory).get(AccountViewModel.class);
        sharedViewModel = new ViewModelProvider(getActivity(), viewModelFactory).get(SharedViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedViewModel.getLoggedGuardian().observe(getViewLifecycleOwner(), guardian -> {
            this.mViewModel.setGuardian(guardian);

            if (isWoman(guardian.getName())) {
                accountFragmentBinding.profileImage.setImageResource(R.drawable.img_mom);
            } else {
                accountFragmentBinding.profileImage.setImageResource(R.drawable.img_dad);
            }

        });
    }

    private boolean isWoman(String name) {
        return name.substring(name.length() - 1).toLowerCase().trim().equals("a");
    }

}
