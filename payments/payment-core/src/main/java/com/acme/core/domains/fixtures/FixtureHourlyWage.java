package com.acme.core.domains.fixtures;

import com.acme.core.commons.utils.TimeUtil;
import com.acme.core.domains.valueObjects.HourlyWage;
import com.acme.core.domains.valueObjects.Period;

import java.util.Arrays;
import java.util.List;

public class FixtureHourlyWage {

    private final static Period NINE_HOURS = Period.newPeriod(TimeUtil.getHour(0, 1), TimeUtil.getHour(9, 0));
    private final static Period EIGHTEEN_HOURS = Period.newPeriod(TimeUtil.getHour(9, 1), TimeUtil.getHour(18, 0));
    private final static Period MIDNIGHT = Period.newPeriod(TimeUtil.getHour(18, 1), TimeUtil.getHour(23, 59));


    public static List<HourlyWage> weekday() {
        return Arrays.asList(
                HourlyWage.builder()
                        .period(NINE_HOURS)
                        .total(25)
                        .build(),
                HourlyWage.builder()
                        .period(EIGHTEEN_HOURS)
                        .total(15)
                        .build(),
                HourlyWage.builder()
                        .period(MIDNIGHT)
                        .total(20)
                        .build());
    }

    public static List<HourlyWage> weekend() {
        return Arrays.asList(
                HourlyWage.builder()
                        .period(NINE_HOURS)
                        .total(30)
                        .build(),
                HourlyWage.builder()
                        .period(EIGHTEEN_HOURS)
                        .total(20)
                        .build(),
                HourlyWage.builder()
                        .period(MIDNIGHT)
                        .total(25)
                        .build());
    }
}
