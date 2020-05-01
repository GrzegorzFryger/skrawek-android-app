package pl.edu.pjatk.pamo.skrawek.module;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pjatk.pamo.skrawek.util.DateUtils;

import static org.junit.Assert.assertNotNull;

public class UtilModuleTest {

    private UtilModule utilModule;

    @Before
    public void setUp() {
        utilModule = new UtilModule();
    }

    @Test
    public void Should_ProvideDateUtil() {
        //Given

        //When
        DateUtils dateUtils = utilModule.dateUtils();

        //Then
        assertNotNull(dateUtils);
    }

}