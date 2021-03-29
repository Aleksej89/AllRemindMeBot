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
    private static final String TIME_PATTERN = ":00";
    private static final String DATE_DELIMITER = ".";
    private static final String DATE_TIME_DELIMITER = " ";
    private static final String DATE_AND_TIME_PATTERN = "dd.MM.yyyy HH:mm:ss";
    private static final String TWO_COUNTER_REGEXP = "\\d{2}[.]\\d{2}";
    private static final String MORE_THAN_TWO_COUNTER_REGEXP = "\\d{2}[.]\\d{2}[.]\\d{4}";

    public static Optional<Date> getDate(String dateStr, String time) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.setTimeZone(TimeZone.getDefault());
        format.applyPattern(DATE_AND_TIME_PATTERN);
        Optional<Date> date = Optional.empty();
        try {
            if (dateStr.matches(TWO_COUNTER_REGEXP)) {
                date = Optional.of(format.parse(dateStr + DATE_DELIMITER + Calendar.getInstance().get(Calendar.YEAR) + DATE_TIME_DELIMITER + time + TIME_PATTERN));
            }
            if (dateStr.matches(MORE_THAN_TWO_COUNTER_REGEXP)) {
                date = Optional.of(format.parse(dateStr + DATE_TIME_DELIMITER + time + TIME_PATTERN));
            }
            if (date.isPresent()) {
                if (date.get().after(new Date())) return date;
            }
        } catch (ParseException exception) {
            log.severe("[ERROR] Converter. Cant parse: " + exception.getMessage());
        }
        return Optional.empty();
    }
}
