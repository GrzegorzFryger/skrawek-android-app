package pl.edu.pjatk.pamo.skrawek.ui.children;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ChildrenSelectFragmentTest {

    @Test
    public void Should_CreateChildrenSelect() {
        //Given

        //When
        ChildrenSelectFragment fragment = ChildrenSelectFragment.newInstance();

        //Then
        assertNotNull(fragment);
    }
}