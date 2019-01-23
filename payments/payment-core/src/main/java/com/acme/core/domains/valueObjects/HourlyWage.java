package com.acme.core.domains.valueObjects;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class HourlyWage implements Serializable {
    private Period period;
    private long total;

    public double calculatePay(Period period) {
        return this.total * this.period.gap(period).length();
    }
}
