package org.allRemindMeBot.enums;

public enum AppCounters {
    ZERO_COUNTER(0),
    SENDER_PRIORITY_COUNTER(1),
    RECEIVER_PRIORITY_COUNTER(3),
    MAXIMUM_CACHE_COUNTER(100),
    CACHE_EXPIRE_COUNTER(2);

    private final int counter;

    AppCounters(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return this.counter;
    }
}
