package pl.edu.pjatk.pamo.skrawek.ui.children;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;

import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Child;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class ChildrenSelectFragmentViewModelTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private Observer<String> stringObserver;

    private Child child;

    private ChildrenSelectViewModel childrenSelectViewModel;

    @Before
    public void setUp() {
        initMocks(this);

        childrenSelectViewModel = new ChildrenSelectViewModel();

        child = new Child();
        child.setName("Adam");
        child.setSurname("Michalczak");
    }

    @Test
    public void Should_GetFullName_From_AccountMutableLiveData() {
        //Given

        //When
        childrenSelectViewModel.selectChild(child);
        LiveData<String> fullName = childrenSelectViewModel.getSelectedChildNameAndSurname();
        fullName.observeForever(stringObserver);

        //Then
        assertEquals("Adam  Michalczak", fullName.getValue());
    }

    @Test
    public void Should_GetDefaultMutableData() {
        //Given

        //When
        LiveData<String> defaultData = childrenSelectViewModel.defaultMessage();
        defaultData.observeForever(stringObserver);

        //Then
        assertEquals("Select children", defaultData.getValue());
    }
}