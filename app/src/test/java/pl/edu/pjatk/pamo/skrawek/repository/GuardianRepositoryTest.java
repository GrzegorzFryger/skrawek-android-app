package pl.edu.pjatk.pamo.skrawek.repository;

import androidx.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.UUID;

import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Guardian;
import pl.edu.pjatk.pamo.skrawek.rest.service.GuardianService;
import retrofit2.Call;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GuardianRepositoryTest {

    @Mock
    private GuardianService guardianService;

    private GuardianRepository guardianRepository;

    @Before
    public void setUp() {
        initMocks(this);
        guardianRepository = new GuardianRepository(guardianService);
    }

    @Test
    public void Should_MutableLiveDataFor_Guardian() {
        //Given
        Call call = mock(Call.class);

        //When
        when(guardianService.getGuardian(any(UUID.class))).thenReturn(call);
        MutableLiveData<Guardian> result = guardianRepository.getGuardian(UUID.randomUUID());

        //Then
        assertNotNull(result);
    }
}