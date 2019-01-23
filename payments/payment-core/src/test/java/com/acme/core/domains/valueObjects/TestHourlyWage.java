package com.acme.core.domains.valueObjects;

import com.acme.core.commons.utils.TimeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class TestHourlyWage {

    @Test
    void shouldCalculatePayWhenPeriodMatch() {

        final HourlyWage hourlyWage = HourlyWage.builder()
                .period(Period.newPeriod(TimeUtil.getHour(10, 0), TimeUtil.getHour(15, 0)))
                .total(10)
                .build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(10, hourlyWage.calculatePay(Period.newPeriod(TimeUtil.getHour(14, 0), TimeUtil.getHour(17, 0)))),
                () -> Assertions.assertEquals(10, hourlyWage.calculatePay(Period.newPeriod(TimeUtil.getHour(9, 0), TimeUtil.getHour(11, 0))))

        );

        Assertions.assertEquals(10, hourlyWage.calculatePay(Period.newPeriod(TimeUtil.getHour(14, 0), TimeUtil.getHour(17, 0))));
    }

    @Test
    void shouldNotCalculatePayWhenPeriodNotMatch() {

        final HourlyWage hourlyWage = HourlyWage.builder()
                .period(Period.newPeriod(TimeUtil.getHour(10, 0), TimeUtil.getHour(15, 0)))
                .total(10)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(0, hourlyWage.calculatePay(Period.newPeriod(TimeUtil.getHour(9, 0), TimeUtil.getHour(9, 59)))),
                () -> Assertions.assertEquals(0, hourlyWage.calculatePay(Period.newPeriod(TimeUtil.getHour(15, 1), TimeUtil.getHour(17, 0))))

        );
    }
}
