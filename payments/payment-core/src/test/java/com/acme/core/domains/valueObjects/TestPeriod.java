package com.acme.core.domains.valueObjects;

import com.acme.core.commons.utils.TimeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.time.LocalTime;

@RunWith(JUnitPlatform.class)
public class TestPeriod {

    @Test
    void shouldCreateNewPeriodWhenRangeIsValid() {
        final LocalTime start = TimeUtil.getHour(10, 0);
        final LocalTime end = TimeUtil.getHour(11, 0);

        Assertions.assertNotNull(Period.newPeriod(start, end));
    }

    @Test
    void shouldNotCreatePeriodWhenRangeIsInvalid() {
        final LocalTime start = TimeUtil.getHour(10, 0);
        final LocalTime end = TimeUtil.getHour(9, 0);

        Assertions.assertThrows(IllegalArgumentException.class, () -> Period.newPeriod(start, end));
    }

    @Test
    void shouldCalculateTheLength() {
        final Period period = Period.newPeriod(TimeUtil.getHour(10, 0), TimeUtil.getHour(15, 0));

        Assertions.assertEquals(5, period.gap(Period.newPeriod(TimeUtil.getHour(10, 0), TimeUtil.getHour(15, 0))).length());
    }

}
