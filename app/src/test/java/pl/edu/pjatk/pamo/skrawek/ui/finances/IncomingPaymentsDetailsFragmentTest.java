package pl.edu.pjatk.pamo.skrawek.ui.finances;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class IncomingPaymentsDetailsFragmentTest {

    @Test
    public void Should_CreateIncomingPaymentsDetailsFragment() {
        //Given

        //When
        IncomingPaymentsDetailsFragment fragment = IncomingPaymentsDetailsFragment.newInstance();

        //Then
        assertNotNull(fragment);
    }
}