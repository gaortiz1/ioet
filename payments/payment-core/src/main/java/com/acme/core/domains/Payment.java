package com.acme.core.domains;

import com.acme.core.domains.types.Day;
import com.acme.core.domains.valueObjects.HourlyWage;
import com.acme.core.domains.valueObjects.Period;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Data
@Builder
public class Payment implements Serializable {
    private Day day;
    private List<HourlyWage> hourlyWages;

    public void addHourlyWage(HourlyWage hourlyWage) {
        if (this.hourlyWages == null) {
            this.hourlyWages = new LinkedList<>();
        }

        this.hourlyWages.add(hourlyWage);
    }

    public double calculatePay(Period period) {
        return this.hourlyWages
                .stream()
                .mapToDouble(hourlyWage -> hourlyWage.calculatePay(period))
                .sum();
    }
}
