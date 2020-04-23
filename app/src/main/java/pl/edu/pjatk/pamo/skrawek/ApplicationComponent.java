package pl.edu.pjatk.pamo.skrawek;

import dagger.Component;
import pl.edu.pjatk.pamo.skrawek.rest.NetworkModule;

@Component(modules = NetworkModule.class)
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);
}
