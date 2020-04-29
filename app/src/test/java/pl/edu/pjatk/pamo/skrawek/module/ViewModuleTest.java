package pl.edu.pjatk.pamo.skrawek.module;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pjatk.pamo.skrawek.ui.snackbar.SnackbarFactory;

import static org.junit.Assert.assertNotNull;

public class ViewModuleTest {

    private ViewModule viewModule;

    @Before
    public void setUp() {
        viewModule = new ViewModule();
    }

    @Test
    public void Should_Create_SnackbarFactory() {
        //Given

        //When
        SnackbarFactory snackbarFactory = viewModule.snackbarFactory();

        //Then
        assertNotNull(snackbarFactory);
    }
}