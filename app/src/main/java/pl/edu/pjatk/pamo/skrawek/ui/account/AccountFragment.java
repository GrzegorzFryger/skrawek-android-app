package pl.edu.pjatk.pamo.skrawek.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.SharedViewModel;

import static pl.edu.pjatk.pamo.skrawek.rest.auth.SessionManager.CITY;
import static pl.edu.pjatk.pamo.skrawek.rest.auth.SessionManager.NAME;
import static pl.edu.pjatk.pamo.skrawek.rest.auth.SessionManager.PHONE;
import static pl.edu.pjatk.pamo.skrawek.rest.auth.SessionManager.POSTAL_CODE;
import static pl.edu.pjatk.pamo.skrawek.rest.auth.SessionManager.STATUS;
import static pl.edu.pjatk.pamo.skrawek.rest.auth.SessionManager.STREET_NUMBER;
import static pl.edu.pjatk.pamo.skrawek.rest.auth.SessionManager.SURNAME;
import static pl.edu.pjatk.pamo.skrawek.rest.auth.SessionManager.getProperty;

public class AccountFragment extends Fragment {

    private AccountViewModel mViewModel;
    private SharedViewModel sharedViewModel;

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.account_fragment, container, false);
        fillFragmentWithData(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        this.sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        // TODO: Use the ViewModel
    }

    private void fillFragmentWithData(View view) {
        updateText(view, R.id.fullNameTextView, getProperty(NAME) + " " + getProperty(SURNAME));
        updateText(view, R.id.cityTextView, getProperty(POSTAL_CODE) + " " + getProperty(CITY));
        updateText(view, R.id.streetTextView, getProperty(STREET_NUMBER));
        updateText(view, R.id.phoneTextView, getProperty(PHONE));
        updateText(view, R.id.accountStatusTextView, getProperty(STATUS));
    }

    private void updateText(View view, int id, String text) {
        TextView textView = view.findViewById(id);
        textView.setText(text);
    }

}
