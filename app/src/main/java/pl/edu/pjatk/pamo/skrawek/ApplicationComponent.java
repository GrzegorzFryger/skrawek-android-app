package pl.edu.pjatk.pamo.skrawek;

import dagger.Component;
import pl.edu.pjatk.pamo.skrawek.rest.NetworkModule;
import pl.edu.pjatk.pamo.skrawek.ui.home.HomeFragment;

@Component(modules = NetworkModule.class)
public interface ApplicationComponent {

    void inject(HomeFragment homeFragment);
}
