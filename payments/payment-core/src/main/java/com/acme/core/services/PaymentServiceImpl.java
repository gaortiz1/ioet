package com.acme.core.services;

import com.acme.core.commons.exceptions.PaymentException;
import com.acme.core.domains.valueObjects.Period;
import com.acme.core.dtos.DayWorkedDTO;
import com.acme.core.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Double calculate(List<DayWorkedDTO> workDays) throws PaymentException {
        this.validateDaysRepeated(workDays);
        return workDays
                .stream()
                .mapToDouble(dayWorkedDTO -> this.paymentRepository
                        .findByDay(dayWorkedDTO.getDay())
                        .calculatePay(Period.newPeriod(dayWorkedDTO.getStart(), dayWorkedDTO.getEnd())))
                .sum();
    }

    /*
    Time execution O(n)
     */
    private void validateDaysRepeated(List<DayWorkedDTO> workDays) throws PaymentException {
        Collections.sort(workDays);
        for (int i = 1; i < workDays.size(); i++) {
            if (workDays.get(i - 1).getDay().equals(workDays.get(i).getDay()))
                throw new PaymentException("day " + workDays.get(i).getDay() + " is repeated");
        }
    }
}