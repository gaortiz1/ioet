package com.acme.core.domains.valueObjects;

import com.acme.core.commons.utils.TimeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class TestPeriodComposer {

    @Test
    void shouldCalculateTheGapWhenPeriodIsEncloseAll() {
        final Period period = Period.newPeriod(TimeUtil.getHour(10, 0), TimeUtil.getHour(15, 0));
        final Period that = Period.newPeriod(TimeUtil.getHour(10, 0), TimeUtil.getHour(15, 0));

        final Period gap = new PeriodComposer().gap(period, that);
        Assertions.assertAll(
                () -> Assertions.assertEquals(TimeUtil.getHour(10, 0), gap.getStart()),
                () -> Assertions.assertEquals(TimeUtil.getHour(15, 0), gap.getEnd())
        );
    }

    @Test
    void shouldCalculateTheGapWhenPeriodIsEncloseUpperBound() {
        final Period period = Period.newPeriod(TimeUtil.getHour(9, 0), TimeUtil.getHour(11, 0));
        final Period that = Period.newPeriod(TimeUtil.getHour(10, 0), TimeUtil.getHour(15, 0));

        final Period gap = new PeriodComposer().gap(period, that);
        Assertions.assertAll(
                () -> Assertions.assertEquals(TimeUtil.getHour(10, 0), gap.getStart()),
                () -> Assertions.assertEquals(TimeUtil.getHour(11, 0), gap.getEnd())
        );
    }

    @Test
    void shouldCalculateTheGapWhenPeriodIsEncloseLowerBound() {
        final Period period = Period.newPeriod(TimeUtil.getHour(12, 0), TimeUtil.getHour(16, 0));
        final Period that = Period.newPeriod(TimeUtil.getHour(10, 0), TimeUtil.getHour(15, 0));

        final Period gap = new PeriodComposer().gap(period, that);
        Assertions.assertAll(
                () -> Assertions.assertEquals(TimeUtil.getHour(12, 0), gap.getStart()),
                () -> Assertions.assertEquals(TimeUtil.getHour(15, 0), gap.getEnd())
        );
    }

    @Test
    void shouldCalculateTheGapWhenPeriodIsOverlap() {
        final Period period = Period.newPeriod(TimeUtil.getHour(9, 0), TimeUtil.getHour(12, 0));
        final Period that = Period.newPeriod(TimeUtil.getHour(10, 0), TimeUtil.getHour(11, 0));

        final Period gap = new PeriodComposer().gap(period, that);
        Assertions.assertAll(
                () -> Assertions.assertEquals(TimeUtil.getHour(10, 0), gap.getStart()),
                () -> Assertions.assertEquals(TimeUtil.getHour(11, 0), gap.getEnd())
        );
    }

    @Test
    void shouldCalculateEmptyGapWhenPeriodNotMatch() {
        final Period period = Period.newPeriod(TimeUtil.getHour(6, 0), TimeUtil.getHour(9, 59));
        final Period that = Period.newPeriod(TimeUtil.getHour(10, 0), TimeUtil.getHour(15, 0));

        final Period gap = new PeriodComposer().gap(period, that);
        Assertions.assertAll(
                () -> Assertions.assertEquals(TimeUtil.getHour(0, 0), gap.getStart()),
                () -> Assertions.assertEquals(TimeUtil.getHour(0, 0), gap.getEnd())
        );

        final Period oppositeGap = new PeriodComposer().gap(that, period);
        Assertions.assertAll(
                () -> Assertions.assertEquals(TimeUtil.getHour(0, 0), oppositeGap.getStart()),
                () -> Assertions.assertEquals(TimeUtil.getHour(0, 0), oppositeGap.getEnd())
        );
    }

}
