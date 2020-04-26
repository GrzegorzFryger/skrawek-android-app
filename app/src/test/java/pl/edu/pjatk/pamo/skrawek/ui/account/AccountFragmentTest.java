package pl.edu.pjatk.pamo.skrawek.ui.account;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AccountFragmentTest {

    @Test
    public void Should_CreateAccountFragment() {
        //Given

        //When
        AccountFragment fragment = AccountFragment.newInstance();

        //Then
        assertNotNull(fragment);
    }

}