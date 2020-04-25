package pl.edu.pjatk.pamo.skrawek.rest.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import pl.edu.pjatk.pamo.skrawek.rest.service.FinancesService;
import retrofit2.Call;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class BalanceControllerTest {

    @Mock
    private FinancesService financesService;

    @InjectMocks
    private BalanceController balanceController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void Should_GetBalanceForChild() {
        //Given
        Call call = mock(Call.class);

        //When
        when(financesService.getBalanceForChild(any(UUID.class))).thenReturn(call);
        balanceController.getBalanceForChild(UUID.randomUUID());

        //Then
        verify(financesService, only()).getBalanceForChild(any(UUID.class));
    }

}