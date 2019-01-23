package com.acme.core.domains.types;

import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

@Getter
public enum Day implements Serializable {

    MONDAY("MO", "Monday"),
    TUESDAY("TU", "Tuesday"),
    WEDNESDAY("WE", "Wednesday"),
    THURSDAY("TH", "Thursday"),
    FRIDAY("FR", "Friday"),
    SATURDAY("SA", "Saturday"),
    SUNDAY("SU", "Sunday");

    private final String code;
    private final String name;

    Day(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Optional<Day> dayOf(String code) {
        return Arrays.stream(values())
                .filter(day -> day.code.equals(code))
                .findFirst();
    }
}
