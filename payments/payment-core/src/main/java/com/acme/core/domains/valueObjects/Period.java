package com.acme.core.domains.valueObjects;

import lombok.Getter;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;

@Getter
public class Period implements Serializable {

    private final LocalTime start;
    private final LocalTime end;

    public Period(LocalTime start, LocalTime end) {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException(start + " after " + end);
        }
        this.start = start;
        this.end = end;
    }

    public double length() {
        Long minutes = Duration.between(this.getStart(), this.getEnd()).toMinutes();
        return Math.ceil(minutes.doubleValue() / 60.0);
    }

    public Period gap(Period period) {
        return new PeriodComposer().gap(period, this);
    }

    public static Period newPeriod(LocalTime start, LocalTime end) {
        return new Period(start, end);
    }
}
