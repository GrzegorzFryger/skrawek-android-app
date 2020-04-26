package pl.edu.pjatk.pamo.skrawek.ui.absence;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AbsenceFragmentTest {

    @Test
    public void Should_CreateAbsenceFragment() {
        //Given

        //When
        AbsenceFragment fragment = AbsenceFragment.newInstance();

        //Then
        assertNotNull(fragment);
    }

}