package org.allRemindMeBot.enums;

public enum Regexps {
    TIME_REGEXP("(\\d{2}[:]\\d{2})"),
    DATE_REGEXP("(\\d{2}[.]\\d{2}[.]\\d{4}|\\d{2}[.]\\d{2})"),
    DATE_TWO_COUNTER_REGEXP("\\d{2}[.]\\d{2}"),
    DATE_MORE_THAN_TWO_COUNTER_REGEXP("\\d{2}[.]\\d{2}[.]\\d{4}"),
    CHARACTER_FILTER_REGEXP("[^\\p{L}\\p{M}\\p{N}\\p{P}\\p{Z}\\p{Cf}\\p{Cs}\\s]");

    private final String regexp;

    Regexps(String regexp) {
        this.regexp = regexp;
    }

    public String getRegexp() {
        return this.regexp;
    }
}
