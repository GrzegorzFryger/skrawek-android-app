package pl.edu.pjatk.pamo.skrawek.ui.snackbar;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import pl.edu.pjatk.pamo.skrawek.R;

/**
 * This factory is responsible for building customized snackbars, which will present some message
 * to application user.
 */
public class SnackbarFactory {

    @Inject
    public SnackbarFactory() {

    }

    /**
     * This method is responsible for building snackbar with error message to user
     */
    public Snackbar buildSnackbarWithErrorMessage(View view, Context context, String message) {
        return initializeSnackbar(view, message, context, R.color.snackbar_red);
    }

    /**
     * This method is responsible for building snackbar with warning message to user
     */
    public Snackbar buildSnackbarWarningMessage(View view, Context context, String message) {
        return initializeSnackbar(view, message, context, R.color.snackbar_yellow);
    }

    /**
     * This method is responsible for building snackbar with success message to user
     */
    public Snackbar buildSnackbarSuccessMessage(View view, Context context, String message) {
        return initializeSnackbar(view, message, context, R.color.snackbar_green);
    }

    /**
     * This method is responsible for building snackbar with info message to user
     */
    public Snackbar buildSnackbarInfoMessage(View view, Context context, String message) {
        return initializeSnackbar(view, message, context, R.color.snackbar_blue);
    }

    private Snackbar initializeSnackbar(View view, String message, Context context, int color) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(context, color));
        TextView snackTextView = snackbarView.findViewById(R.id.snackbar_text);
        snackTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        snackTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        return snackbar;
    }
}
