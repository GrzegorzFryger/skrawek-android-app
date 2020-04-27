package pl.edu.pjatk.pamo.skrawek;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Child;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Child> selectedChild = new MutableLiveData<>();

    public SharedViewModel() {
    }

    public void selectChild(Child child) {
        selectedChild.setValue(child);
    }

    public LiveData<Child> getSelectedChild() {
        return selectedChild;
    }
}
