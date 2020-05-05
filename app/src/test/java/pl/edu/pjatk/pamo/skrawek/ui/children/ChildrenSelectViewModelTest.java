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
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Gender;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class ChildrenSelectViewModelTest {
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
        child.setCity("Some city");
        child.setGender(Gender.FEMALE);
        child.setDateOfBirth("2015-05-05");
        child.setPesel("10235234123");
        child.setPostalCode("10-111");
        child.setStartDate("2020-05-10");
        child.setEndDate("2022-05-10");
        child.setStreetNumber("Some street number");
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