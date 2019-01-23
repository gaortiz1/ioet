package com.acme.core.services;

import com.acme.core.commons.exceptions.PaymentException;
import com.acme.core.dtos.DayWorkedDTO;

import java.util.List;

public interface PaymentService {

    Double calculate(List<DayWorkedDTO> workDays) throws PaymentException;

}
