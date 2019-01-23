package com.acme.core.domains.fixtures;

import com.acme.core.domains.Payment;
import com.acme.core.domains.types.Day;

import java.util.HashSet;
import java.util.Set;

public class FixturePayment {

    public final static Set<Payment> PAYMENTS;

    static {
        PAYMENTS = new HashSet<>();

        PAYMENTS.add(Payment.builder()
                .day(Day.MONDAY)
                .hourlyWages(FixtureHourlyWage.weekday())
                .build());

        PAYMENTS.add(Payment.builder()
                .day(Day.TUESDAY)
                .hourlyWages(FixtureHourlyWage.weekday())
                .build());

        PAYMENTS.add(Payment.builder()
                .day(Day.WEDNESDAY)
                .hourlyWages(FixtureHourlyWage.weekday())
                .build());

        PAYMENTS.add(Payment.builder()
                .day(Day.THURSDAY)
                .hourlyWages(FixtureHourlyWage.weekday())
                .build());

        PAYMENTS.add(Payment.builder()
                .day(Day.FRIDAY)
                .hourlyWages(FixtureHourlyWage.weekday())
                .build());

        PAYMENTS.add(Payment.builder()
                .day(Day.SATURDAY)
                .hourlyWages(FixtureHourlyWage.weekend())
                .build());

        PAYMENTS.add(Payment.builder()
                .day(Day.SUNDAY)
                .hourlyWages(FixtureHourlyWage.weekend())
                .build());
    }
}
