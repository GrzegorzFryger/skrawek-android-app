package pl.edu.pjatk.pamo.skrawek.util;

import android.net.ParseException;
import android.util.Log;

import com.applandeo.materialcalendarview.EventDay;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import pl.edu.pjatk.pamo.skrawek.R;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.DayOffWork;
import pl.edu.pjatk.pamo.skrawek.rest.model.calendar.EventType;

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

    public EventDay prepareEventDay(DayOffWork dayOffWork) {
        Calendar calendar = toCalendar(LocalDate.parse(dayOffWork.getDate()));
        if (EventType.HOLIDAY.equals(dayOffWork.getEventType())) {
            return new EventDay(calendar, R.drawable.red_circle);
        } else if (EventType.INTERNAL_EVENT.equals(dayOffWork.getEventType())) {
            return new EventDay(calendar, R.drawable.purple_circle);
        }
        return new EventDay(calendar);
    }
}
