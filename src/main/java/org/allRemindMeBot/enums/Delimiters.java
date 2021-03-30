package org.allRemindMeBot.enums;

public enum Delimiters {
    NON_WHITE_SPACE_DELIMITER(""),
    ONE_WHITE_SPACE_DELIMITER(" "),
    TWO_WHITE_SPACE_DELIMITER("  "),
    DATE_DELIMITER("."),
    TIME_DELIMITER(":");
    private final String delimiter;

    Delimiters(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return this.delimiter;
    }
}
