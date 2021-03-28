package org.allRemindMeBot.enums;

public enum ServiceCounters {
    SERVICE_SLEEP_MILLS_COUNTER(1000);

    private final int counter;

    ServiceCounters(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return this.counter;
    }
}
