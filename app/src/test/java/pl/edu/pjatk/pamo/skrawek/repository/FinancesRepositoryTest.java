package pl.edu.pjatk.pamo.skrawek.repository;

import androidx.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.UUID;

import pl.edu.pjatk.pamo.skrawek.rest.model.finances.Balance;
import pl.edu.pjatk.pamo.skrawek.rest.model.finances.IncomingPayment;
import pl.edu.pjatk.pamo.skrawek.rest.service.FinancesService;
import retrofit2.Call;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class FinancesRepositoryTest {
    @Mock
    private FinancesService financesService;

    private FinancesRepository financesRepository;

    @Before
    public void setUp() {
        initMocks(this);
        financesRepository = new FinancesRepository(financesService);
    }

    @Test
    public void Should_MutableLiveDataFor_IncomingPaymentsForChildren() {
        //Given
        Call call = mock(Call.class);

        //When
        when(financesService.getAllIncomingPaymentsForChild(any(UUID.class))).thenReturn(call);
        MutableLiveData<List<IncomingPayment>> result =
                financesRepository.getIncomingPaymentsForChildren(UUID.randomUUID());

        //Then
        assertNotNull(result);
    }

    @Test
    public void Should_MutableLiveDataFor_ChildBalance() {
        //Given
        Call call = mock(Call.class);

        //When
        when(financesService.getBalanceForChild(any(UUID.class))).thenReturn(call);
        MutableLiveData<Balance> result =
                financesRepository.getBalance(UUID.randomUUID());

        //Then
        assertNotNull(result);
    }
}