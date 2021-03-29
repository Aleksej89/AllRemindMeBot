package org.allRemindMeBot.bot.converters;

import lombok.extern.java.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

@Log
public class DateConverter {
    private static final String TWO_COUNTER_REGEXP = "\\d{2}[.]\\d{2}";
    private static final String MORE_THAN_TWO_COUNTER_REGEXP = "\\d{2}[.]\\d{2}[.]\\d{4}";

    public static Optional<Date> getDate(String dateStr, String time) {
        Optional<Date> date = Optional.empty();
        SimpleDateFormat format = new SimpleDateFormat();
        format.setTimeZone(TimeZone.getDefault());
        format.applyPattern("dd.MM.yyyy HH:mm:ss");
        try {
            if (dateStr.matches(TWO_COUNTER_REGEXP)) {
                date = Optional.of(format.parse(dateStr + "." + Calendar.getInstance().get(Calendar.YEAR) + " " + time + ":00"));
            }
            if (dateStr.matches(MORE_THAN_TWO_COUNTER_REGEXP)) {
                date = Optional.of(format.parse(dateStr + " " + time + ":00"));
            }
            if (date.isPresent()) {
                if (date.get().after(new Date())) return date;
            }
        } catch (ParseException exception) {
            date = Optional.empty();
            log.severe("[ERROR] Converter. Cant parse: " + exception.getMessage());
        }
        return date;
    }
}
