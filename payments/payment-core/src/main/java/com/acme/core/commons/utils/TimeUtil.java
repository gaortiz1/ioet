package com.acme.core.commons.utils;

import java.time.LocalTime;

public class TimeUtil {

    private TimeUtil() {
    }

    public static LocalTime getHour(int hour, int minute) {
        return LocalTime.of(hour, minute, 0);
    }

    public static boolean greaterThanOrEqual(LocalTime localTime, LocalTime other) {
        return localTime.compareTo(other) >= 0;
    }

    public static boolean lessThanOrEqual(LocalTime localTime, LocalTime other) {
        return localTime.compareTo(other) <= 0;
    }

    public static LocalTime parse(String hour) {
        return LocalTime.parse(hour);
    }

}
