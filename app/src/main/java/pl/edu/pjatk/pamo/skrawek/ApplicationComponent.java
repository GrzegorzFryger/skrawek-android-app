package pl.edu.pjatk.pamo.skrawek;

import dagger.Component;
import pl.edu.pjatk.pamo.skrawek.rest.RestModule;

@Component(modules = RestModule.class)
public interface ApplicationComponent {

    void inject(LoginActivity loginActivity);
}
