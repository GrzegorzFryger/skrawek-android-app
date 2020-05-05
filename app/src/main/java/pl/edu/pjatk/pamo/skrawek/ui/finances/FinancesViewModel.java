package pl.edu.pjatk.pamo.skrawek.ui.finances;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.repository.FinancesRepository;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Child;
import pl.edu.pjatk.pamo.skrawek.rest.model.finances.Balance;
import pl.edu.pjatk.pamo.skrawek.rest.model.finances.IncomingPayment;

/**
 * This view model stores info about all incoming payments for given child
 */
public class FinancesViewModel extends ViewModel {
    private final FinancesRepository financesRepository;
    private MutableLiveData<Child> selectedChild = new MutableLiveData<>();
    private MutableLiveData<IncomingPayment> selectedIncomingPayment = new MutableLiveData<>();

    private LiveData<Balance> balanceLiveData;
    private LiveData<List<IncomingPayment>> incomingPaymentLiveData;

    @Inject
    public FinancesViewModel(FinancesRepository financesRepository) {
        this.financesRepository = financesRepository;

        this.balanceLiveData = Transformations.switchMap(
                selectedChild,
                s -> this.financesRepository.getBalance(s.getId())
        );

        incomingPaymentLiveData = Transformations.switchMap(
                selectedChild,
                s -> this.financesRepository.getIncomingPaymentsForChildren(s.getId())
        );
    }

    public void selectChild(Child child) {
        selectedChild.setValue(child);
    }

    public void selectIncomingPayment(IncomingPayment incomingPayment) {
        this.selectedIncomingPayment.setValue(incomingPayment);
    }

    public LiveData<List<IncomingPayment>> getIncomingPayments() {
        return incomingPaymentLiveData;
    }

    public LiveData<IncomingPayment> getSelectedIncomingPayment() {
        return selectedIncomingPayment;
    }

    public LiveData<Balance> getBalance() {
        return balanceLiveData;
    }
}
