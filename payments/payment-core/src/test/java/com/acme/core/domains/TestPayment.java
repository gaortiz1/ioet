package com.acme.core.domains;

import com.acme.core.commons.utils.TimeUtil;
import com.acme.core.domains.types.Day;
import com.acme.core.domains.valueObjects.HourlyWage;
import com.acme.core.domains.valueObjects.Period;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPayment {

    @Test
    void shouldCalculatePayWhenItIsWithinRange() {

        final Payment payment = Payment.builder()
                .day(Day.MONDAY)
                .build();

        payment.addHourlyWage(HourlyWage.builder()
                .total(25)
                .period(Period.newPeriod(TimeUtil.getHour(0, 1), TimeUtil.getHour(12, 0)))
                .build());

        payment.addHourlyWage(HourlyWage.builder()
                .total(20)
                .period(Period.newPeriod(TimeUtil.getHour(12, 1), TimeUtil.getHour(23, 50)))
                .build());

        Assertions.assertEquals(45, payment.calculatePay(Period.newPeriod(TimeUtil.getHour(11, 0), TimeUtil.getHour(13, 0))));
    }
}
