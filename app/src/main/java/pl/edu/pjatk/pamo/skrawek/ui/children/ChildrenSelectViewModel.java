package pl.edu.pjatk.pamo.skrawek.ui.children;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Child;

/**
 * This view model stores info about currently selected child
 */
public class ChildrenSelectViewModel extends ViewModel {
    private String NAME_SURNAME_TEMPLATE = "%s  %s";
    private String DEFAULT_MESSAGE = "Select children";
    private MutableLiveData<Child> selectedChild = new MutableLiveData<>();

    @Inject
    public ChildrenSelectViewModel() {
    }

    public LiveData<String> getSelectedChildNameAndSurname() {
        return Transformations.map(selectedChild, selectedChild ->
                String.format(NAME_SURNAME_TEMPLATE, selectedChild.getName(), selectedChild.getSurname())
        );
    }

    public void selectChild(Child child) {
        this.getSelectedChild().setValue(child);
    }

    public MutableLiveData<Child> getSelectedChild() {
        return selectedChild;
    }

    public LiveData<String> defaultMessage() {
        return new MutableLiveData<>(this.DEFAULT_MESSAGE);
    }


}
