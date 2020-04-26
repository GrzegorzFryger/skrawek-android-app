package pl.edu.pjatk.pamo.skrawek.ui.finances;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class FinancesFragmentTest {

    @Test
    public void Should_CreateFinancesFragment() {
        //Given

        //When
        FinancesFragment fragment = FinancesFragment.newInstance();

        //Then
        assertNotNull(fragment);
    }
}