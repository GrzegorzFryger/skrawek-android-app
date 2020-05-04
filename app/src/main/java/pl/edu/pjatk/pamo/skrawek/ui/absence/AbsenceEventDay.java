package pl.edu.pjatk.pamo.skrawek.ui.absence;

import androidx.annotation.DrawableRes;

import com.applandeo.materialcalendarview.EventDay;

import java.util.Calendar;

/**
 * This class is used in Calendar.
 * It extends standard event day with information of event description.
 */
public class AbsenceEventDay extends EventDay {
    private String eventDescription;

    public AbsenceEventDay(Calendar day, @DrawableRes int drawable, String eventDescription) {
        super(day, drawable);
        this.eventDescription = eventDescription;
    }

    public AbsenceEventDay(Calendar day, String eventDescription) {
        super(day);
        this.eventDescription = eventDescription;
    }

    public String getEventDescription() {
        return eventDescription != null ? eventDescription : "";
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
}
