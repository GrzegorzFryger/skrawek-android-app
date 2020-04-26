package pl.edu.pjatk.pamo.skrawek.ui.children;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ChildrenSelectTest {

    @Test
    public void Should_CreateChildrenSelect() {
        //Given

        //When
        ChildrenSelect fragment = ChildrenSelect.newInstance();

        //Then
        assertNotNull(fragment);
    }
}