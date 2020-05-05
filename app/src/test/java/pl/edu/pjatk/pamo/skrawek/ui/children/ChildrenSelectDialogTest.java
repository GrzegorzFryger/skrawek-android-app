package pl.edu.pjatk.pamo.skrawek.ui.children;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ChildrenSelectDialogTest {

    @Test
    public void Should_CreateChildrenSelectDialog() {
        //Given

        //When
        ChildrenSelectDialog childrenSelectDialog = ChildrenSelectDialog.newInstance();

        //Then
        assertNotNull(childrenSelectDialog);
    }
}