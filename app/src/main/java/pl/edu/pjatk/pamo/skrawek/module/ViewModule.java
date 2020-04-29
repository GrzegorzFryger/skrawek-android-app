package pl.edu.pjatk.pamo.skrawek.module;

import dagger.Module;
import dagger.Provides;
import pl.edu.pjatk.pamo.skrawek.ui.snackbar.SnackbarFactory;

/**
 * This module is responsible for providing classes that are used for creating view elements
 */
@Module
public class ViewModule {

    @Provides
    public SnackbarFactory snackbarFactory() {
        return new SnackbarFactory();
    }
}
