package pl.edu.pjatk.pamo.skrawek.ui.home;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class HomeFragmentTest {

    @Test
    public void Should_CreateHomeFragment() {
        //Given

        //When
        HomeFragment fragment = HomeFragment.newInstance();

        //Then
        assertNotNull(fragment);
    }
}