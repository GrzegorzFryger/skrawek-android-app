package pl.edu.pjatk.pamo.skrawek;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MyApplicationTest {

    private MyApplication myApplication;

    @Before
    public void setUp() {
        myApplication = new MyApplication();
    }

    @Test
    public void Should_CreateAppComponent() {
        //Given

        //When
        ApplicationComponent result = myApplication.getAppComponent();

        //Then
        assertNotNull(result);
    }
}