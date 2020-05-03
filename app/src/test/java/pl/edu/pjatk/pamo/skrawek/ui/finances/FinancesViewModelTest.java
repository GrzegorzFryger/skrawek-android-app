package pl.edu.pjatk.pamo.skrawek.ui.finances;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import pl.edu.pjatk.pamo.skrawek.repository.FinancesRepository;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Child;
import pl.edu.pjatk.pamo.skrawek.rest.model.finances.Balance;
import pl.edu.pjatk.pamo.skrawek.rest.model.finances.IncomingPayment;
import pl.edu.pjatk.pamo.skrawek.rest.model.finances.PaymentType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class FinancesViewModelTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private Balance balance;
    private IncomingPayment incomingPayment;
    private List<IncomingPayment> incomingPayments;

    @Mock
    private Observer<List<IncomingPayment>> listObserver;
    @Mock
    private Observer<IncomingPayment> paymentObserver;
    @Mock
    private Observer<Balance> balanceObserver;
    @Mock
    private FinancesRepository financesRepository;

    private MutableLiveData<List<IncomingPayment>> incomingPaymentListMutableLiveData;
    private MutableLiveData<Balance> balanceMutableLiveData;

    private FinancesViewModel financesViewModel;

    @Before
    public void setUp() {
        initMocks(this);

        Child child = new Child();
        child.setId(UUID.randomUUID());

        incomingPayment = new IncomingPayment();
        incomingPayment.setChildId(UUID.randomUUID());
        incomingPayment.setContractorDetails("Contractor details");
        incomingPayment.setGuardianId(UUID.randomUUID());
        incomingPayment.setPaymentType(PaymentType.CASH);
        incomingPayment.setTitle("Title");
        incomingPayment.setTransactionAmount(new BigDecimal(100));
        incomingPayment.setTransactionCurrency("PLN");
        incomingPayment.setTransactionDate("2020-05-05");

        incomingPayments = new ArrayList<>();
        incomingPayments.add(incomingPayment);
        incomingPaymentListMutableLiveData = new MutableLiveData<>();
        incomingPaymentListMutableLiveData.setValue(incomingPayments);

        balance = new Balance();
        balance.setBalance(new BigDecimal(100));
        balance.setLiabilities(new BigDecimal(100));
        balance.setReceivables(new BigDecimal(100));
        balance.setChildId(UUID.randomUUID());
        balance.setGuardianId(UUID.randomUUID());
        balanceMutableLiveData = new MutableLiveData<>();
        balanceMutableLiveData.setValue(balance);

        financesViewModel = new FinancesViewModel(financesRepository);
        financesViewModel.selectChild(child);
    }

    @Test
    public void Should_GetBalanceMutableData() {
        //Given

        //When
        when(financesRepository.getBalance(any(UUID.class))).thenReturn(balanceMutableLiveData);
        LiveData<Balance> balanceLiveData = financesViewModel.getBalance();
        balanceLiveData.observeForever(balanceObserver);
        Balance result = balanceLiveData.getValue();

        //Then
        assertEquals(balance.getBalance(), result.getBalance());
        assertEquals(balance.getReceivables(), result.getReceivables());
        assertEquals(balance.getLiabilities(), result.getLiabilities());
        assertEquals(balance.getChildId(), result.getChildId());
        assertEquals(balance.getGuardianId(), result.getGuardianId());
    }

    @Test
    public void Should_GetIncomingPaymentsMutableData() {
        //Given
        List<IncomingPayment> incomingPayments = new ArrayList<>();
        incomingPayments.add(incomingPayment);

        //When
        when(financesRepository.getIncomingPaymentsForChildren(any(UUID.class)))
                .thenReturn(incomingPaymentListMutableLiveData);
        LiveData<List<IncomingPayment>> listLiveData = financesViewModel.getIncomingPayments();
        listLiveData.observeForever(listObserver);
        List<IncomingPayment> result = listLiveData.getValue();

        //Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(incomingPayment.getChildId(), result.get(0).getChildId());
        assertEquals(incomingPayment.getGuardianId(), result.get(0).getGuardianId());
        assertEquals(incomingPayment.getContractorDetails(), result.get(0).getContractorDetails());
        assertEquals(incomingPayment.getPaymentType(), result.get(0).getPaymentType());
        assertEquals(incomingPayment.getTitle(), result.get(0).getTitle());
        assertEquals(incomingPayment.getTransactionAmount(), result.get(0).getTransactionAmount());
        assertEquals(incomingPayment.getTransactionCurrency(), result.get(0).getTransactionCurrency());
        assertEquals(incomingPayment.getTransactionDate(), result.get(0).getTransactionDate());
    }

    @Test
    public void Should_GetIncomingPaymentMutableData() {
        //Given

        //When
        financesViewModel.selectIncomingPayment(incomingPayment);
        LiveData<IncomingPayment> incomingPaymentLiveData = financesViewModel.getSelectedIncomingPayment();
        incomingPaymentLiveData.observeForever(paymentObserver);
        IncomingPayment result = incomingPaymentLiveData.getValue();

        //Then
        assertNotNull(result);
    }

}