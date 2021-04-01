package org.allRemindMeBot.enums;

public enum Patterns {
    TIME_ZERO_PATTERN("00"),
    DATE_PATTERN("dd.MM.yyyy"),
    DATE_AND_TIME_PATTERN("dd.MM.yyyy HH:mm:ss");
    private final String pattern;

    Patterns(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return this.pattern;
    }
}
