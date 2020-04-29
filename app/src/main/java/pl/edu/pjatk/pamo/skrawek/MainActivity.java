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

import pl.edu.pjatk.pamo.skrawek.ui.DaggerViewModelFactory;
import pl.edu.pjatk.pamo.skrawek.ui.account.AccountViewModel;

import static pl.edu.pjatk.pamo.skrawek.rest.auth.SessionManager.getEmail;

public class MainActivity extends AppCompatActivity {

    @Inject
    DaggerViewModelFactory viewModelFactory;
    AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_finances, R.id.navigation_absence, R.id.navigation_account)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        //todo temporary removed
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.incomingPaymentsDetailsFragment) {
                navView.setVisibility(View.GONE);
            } else {
                navView.setVisibility(View.VISIBLE);
            }
        });

//        navController.addOnDestinationChangedListener(s -> );

        // make status-bar transparent
        this.setTransparentTitleBar(getWindow());

        ((MyApplication) getApplication()).getAppComponent().inject(this);
        initializeAccountViewModel();
    }

    protected void setTransparentTitleBar(Window window) {
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.TRANSPARENT);

        // set color icon to dark
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    protected void initializeAccountViewModel() {
        accountViewModel = new ViewModelProvider(this, viewModelFactory).get(AccountViewModel.class);
        accountViewModel.initializeAccountData(getEmail());
    }
}
