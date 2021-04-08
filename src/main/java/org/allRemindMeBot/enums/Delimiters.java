package org.allRemindMeBot.enums;

public enum Delimiters {
    ONE_LINE_DELIMITER("\n"),
    TWO_LINE_DELIMITER("\n\n"),
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
