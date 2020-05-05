package pl.edu.pjatk.pamo.skrawek.ui.children;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import pl.edu.pjatk.pamo.skrawek.repository.GuardianRepository;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Child;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Guardian;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ChildrenSelectDialogViewModelTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private GuardianRepository guardianRepository;

    @Mock
    private Observer<Guardian> guardianObserver;

    private ChildrenSelectDialogViewModel selectDialogViewModel;

    @Before
    public void setUp() {
        initMocks(this);

        Guardian guardian = new Guardian();
        guardian.setName("Antoni");
        guardian.setSurname("Kowalski");

        List<Child> children = new ArrayList<>();
        children.add(new Child());
        guardian.setChildren(children);

        MutableLiveData<Guardian> data = new MutableLiveData<>();
        data.setValue(guardian);

        when(guardianRepository.getGuardian(any(UUID.class))).thenReturn(data);

        selectDialogViewModel = new ChildrenSelectDialogViewModel(guardianRepository);
        selectDialogViewModel.getGuardianIdPublisher().setValue(UUID.randomUUID());
    }

    @Test
    public void Should_GetGuardianIdPublisher() {
        //Given

        //When
        LiveData<UUID> publisher = selectDialogViewModel.getGuardianIdPublisher();

        //Then
        assertNotNull(publisher);
    }

    @Test
    public void Should_GetGuardianData() {
        //Given

        //When
        LiveData<Guardian> guardianLiveData = selectDialogViewModel.getGuardianLiveData();
        guardianLiveData.observeForever(guardianObserver);

        //Then
        assertNotNull(guardianLiveData);
        assertNotNull(guardianLiveData.getValue());
        assertNotNull(guardianLiveData.getValue().getChildren());
    }
}