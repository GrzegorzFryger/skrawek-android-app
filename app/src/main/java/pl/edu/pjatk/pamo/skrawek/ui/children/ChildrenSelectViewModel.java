package pl.edu.pjatk.pamo.skrawek.ui.children;

import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.UUID;

import pl.edu.pjatk.pamo.skrawek.repository.GuardianRepository;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Child;
import pl.edu.pjatk.pamo.skrawek.rest.model.accounts.Guardian;

public class ChildrenSelectViewModel extends ViewModel {
    private String NAME_SURNAME_TEMPLATE = "%s  %s";
    private String DEFAULT_MESSAGE = "Select children";
    private MutableLiveData<Child> selectedChild = new MutableLiveData<>();


    public ChildrenSelectViewModel() {
    }

    public LiveData<String> getSelectedChildNameAndSurname() {
        return Transformations.map(selectedChild, selectedChild ->
                String.format(NAME_SURNAME_TEMPLATE, selectedChild.getName(), selectedChild.getSurname())
        );
    }

    public MutableLiveData<Child> getSelectedChild() {
        return selectedChild;
    }

    public LiveData<String> getGuardianLiveData() {
       return new MutableLiveData<>(this.DEFAULT_MESSAGE);
    }



}
