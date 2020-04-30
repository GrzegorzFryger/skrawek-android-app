package pl.edu.pjatk.pamo.skrawek.module;

import dagger.Module;
import dagger.Provides;
import pl.edu.pjatk.pamo.skrawek.util.DateUtils;

/**
 * This module is responsible for injecting util classes
 */
@Module
public class UtilModule {

    @Provides
    public DateUtils dateUtils() {
        return new DateUtils();
    }
}
