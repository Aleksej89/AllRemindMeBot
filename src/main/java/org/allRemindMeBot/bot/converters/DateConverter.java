package org.allRemindMeBot.bot.converters;

import lombok.extern.java.Log;
import org.allRemindMeBot.enums.Delimiters;
import org.allRemindMeBot.enums.Patterns;
import org.allRemindMeBot.enums.Regexps;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

@Log
public class DateConverter {

    public static Optional<Date> getDate(String dateStr, String time) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.setTimeZone(TimeZone.getDefault());
        format.applyPattern(Patterns.DATE_AND_TIME_PATTERN.getPattern());
        Optional<Date> date = Optional.empty();
        try {
            if (dateStr != null) {
                if (dateStr.matches(Regexps.DATE_TWO_COUNTER_REGEXP.getRegexp())) {
                    date = Optional.of(format.parse(dateStr + Delimiters.DATE_DELIMITER.getDelimiter() +
                            Calendar.getInstance().get(Calendar.YEAR) + Delimiters.ONE_WHITE_SPACE_DELIMITER.getDelimiter() + time + Delimiters.TIME_DELIMITER.getDelimiter() +
                            Patterns.TIME_ZERO_PATTERN.getPattern()));
                }
                if (dateStr.matches(Regexps.DATE_MORE_THAN_TWO_COUNTER_REGEXP.getRegexp())) {
                    date = Optional.of(format.parse(dateStr + Delimiters.ONE_WHITE_SPACE_DELIMITER.getDelimiter() + time + Delimiters.TIME_DELIMITER.getDelimiter() +
                            Patterns.TIME_ZERO_PATTERN.getPattern()));
                }
            } else {
                date = Optional.of(format.parse(new SimpleDateFormat(Patterns.DATE_PATTERN.getPattern()).format(new Date()) + Delimiters.ONE_WHITE_SPACE_DELIMITER.getDelimiter() +
                        time + Delimiters.TIME_DELIMITER.getDelimiter() + Patterns.TIME_ZERO_PATTERN.getPattern()));
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
