package pl.edu.pjatk.pamo.skrawek.util;

import android.net.ParseException;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.Absence;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.DayOffWork;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.EventType;
import pl.edu.pjatk.pamo.skrawek.ui.absence.AbsenceEventDay;

public class DateUtils {
    private static final String TAG = "DateUtils";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final ZoneId defaultZoneId = ZoneId.systemDefault();

    public Calendar toCalendar(LocalDate localDate) {
        Date date = toDate(localDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public String calendarToString(Calendar calendar) {
        Date date = calendar.getTime();

        String result = "";
        try {
            result = dateFormat.format(date);
        } catch (ParseException pe) {
            Log.e(TAG, "Failed to parse date to string");
        }
        return result;
    }

    public Date toDate(LocalDate dateToConvert) {
        return Date.from(dateToConvert.atStartOfDay(defaultZoneId).toInstant());
    }

    public AbsenceEventDay prepareEventDay(DayOffWork dayOffWork) {
        Calendar calendar = toCalendar(LocalDate.parse(dayOffWork.getDate()));
        if (EventType.HOLIDAY.equals(dayOffWork.getEventType())) {
            return new AbsenceEventDay(calendar, R.drawable.red_circle, dayOffWork.getName());
        } else if (EventType.INTERNAL_EVENT.equals(dayOffWork.getEventType())) {
            return new AbsenceEventDay(calendar, R.drawable.purple_circle, dayOffWork.getName());
        } else if (EventType.WEEKEND.equals(dayOffWork.getEventType())) {
            return new AbsenceEventDay(calendar, dayOffWork.getName());
        }
        return new AbsenceEventDay(calendar, dayOffWork.getName());
    }

    public AbsenceEventDay prepareEventDay(Absence absence, String reason) {
        Calendar calendar = toCalendar(LocalDate.parse(absence.getDate()));
        return new AbsenceEventDay(calendar, R.drawable.green_circle,
                reason);
    }
}
