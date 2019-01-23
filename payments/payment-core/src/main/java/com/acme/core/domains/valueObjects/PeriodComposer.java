package com.acme.core.domains.valueObjects;

import com.acme.core.commons.utils.TimeUtil;

public class PeriodComposer {

    public Period gap(Period period, Period that) {

        if (notMatch(period, that)) {
            return Period.newPeriod(TimeUtil.getHour(0, 0), TimeUtil.getHour(0, 0));
        } else if (encloseAll(period, that)) {
            return Period.newPeriod(period.getStart(), that.getEnd());
        } else if (encloseLowerBound(period, that)) {
            return Period.newPeriod(period.getStart(), that.getEnd());
        } else if (encloseUpperBound(period, that)) {
            return Period.newPeriod(that.getStart(), period.getEnd());
        } else if (overlap(period, that)) {
            return Period.newPeriod(that.getStart(), that.getEnd());
        } else
            return Period.newPeriod(TimeUtil.getHour(0, 0), TimeUtil.getHour(0, 0));
    }

    private boolean overlap(Period period, Period that) {
        return TimeUtil.lessThanOrEqual(period.getStart(), that.getStart())
                && TimeUtil.greaterThanOrEqual(period.getEnd(), that.getEnd());
    }

    private boolean encloseAll(Period period, Period that) {
        return TimeUtil.greaterThanOrEqual(period.getStart(), that.getStart())
                && TimeUtil.lessThanOrEqual(period.getEnd(), that.getEnd());
    }

    private boolean encloseLowerBound(Period period, Period that) {
        return TimeUtil.greaterThanOrEqual(period.getStart(), that.getStart())
                && !TimeUtil.lessThanOrEqual(period.getEnd(), that.getEnd());
    }

    private boolean encloseUpperBound(Period period, Period that) {
        return !TimeUtil.greaterThanOrEqual(period.getStart(), that.getStart())
                && TimeUtil.lessThanOrEqual(period.getEnd(), that.getEnd());
    }

    private boolean notMatch(Period period, Period that) {
        return TimeUtil.lessThanOrEqual(period.getEnd(), that.getStart())
                || TimeUtil.greaterThanOrEqual(period.getStart(), that.getEnd());
    }
}
