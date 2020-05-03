package pl.edu.pjatk.pamo.skrawek;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.repository.AccountRepository;
import pl.edu.pjatk.pamo.skrawek.ui.DaggerViewModelFactory;
import pl.edu.pjatk.pamo.skrawek.ui.absence.DayOffWorkViewModel;
import pl.edu.pjatk.pamo.skrawek.ui.account.AccountViewModel;
import pl.edu.pjatk.pamo.skrawek.ui.snackbar.SnackbarFactory;

import static pl.edu.pjatk.pamo.skrawek.MyApplication.getStringFromRes;
import static pl.edu.pjatk.pamo.skrawek.rest.auth.SessionManager.getEmail;

/**
 * This is main activity - which contains core functions.
 * These are available once user successfully logs in
 */
public class MainActivity extends AppCompatActivity {

    @Inject
    DaggerViewModelFactory viewModelFactory;
    @Inject
    SnackbarFactory snackbarFactory;
    @Inject
    AccountRepository accountRepository;

    AccountViewModel accountViewModel;
    DayOffWorkViewModel dayOffWorkViewModel;
    SharedViewModel sharedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_finances, R.id.navigation_absence, R.id.navigation_account)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.incomingPaymentsDetailsFragment) {
                navView.setVisibility(View.GONE);
            } else {
                navView.setVisibility(View.VISIBLE);
            }
        });

        initializeViewModels();
        initializeSnackBar();

        this.setTransparentTitleBar(getWindow());
    }

    protected void setTransparentTitleBar(Window window) {
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    protected void initializeViewModels() {
        ((MyApplication) getApplication()).getAppComponent().inject(this);
        dayOffWorkViewModel = new ViewModelProvider(this, viewModelFactory).get(DayOffWorkViewModel.class);
        dayOffWorkViewModel.initializeData();

        sharedViewModel = new ViewModelProvider(this, viewModelFactory).get(SharedViewModel.class);

        this.accountRepository.getMutableLiveData(getEmail()).observe(this, s -> {
            this.sharedViewModel.setLoggedAccount(s);
        });

        this.sharedViewModel.getLoggedGuardian().observe(this, guardian -> {
            if (!guardian.getChildren().isEmpty()) {
                this.sharedViewModel.selectChild(guardian.getChildren().get(0));
            }
        });
    }

    protected void initializeSnackBar() {
        snackbarFactory.buildSnackbarSuccessMessage(findViewById(android.R.id.content),
                getApplicationContext(), getStringFromRes(R.string.rest_login_sucess))
                .setDuration(1000) // Login message should be shorter so that user can start using nav menu
                .show();
    }
}
