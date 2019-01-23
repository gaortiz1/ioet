package com.acme.core.services;

import com.acme.core.commons.exceptions.PaymentException;
import com.acme.core.commons.utils.TimeUtil;
import com.acme.core.domains.Payment;
import com.acme.core.domains.types.Day;
import com.acme.core.domains.valueObjects.HourlyWage;
import com.acme.core.domains.valueObjects.Period;
import com.acme.core.dtos.DayWorkedDTO;
import com.acme.core.repositories.PaymentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class TestPaymentService {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Test
    void shouldValidateDaysWorkedWhenItAreRepeated() {

        final List<DayWorkedDTO> workDays = Arrays.asList(
                DayWorkedDTO.builder()
                        .day(Day.MONDAY)
                        .start(TimeUtil.getHour(0, 1))
                        .end(TimeUtil.getHour(9, 0))
                        .build(),
                DayWorkedDTO.builder()
                        .day(Day.MONDAY)
                        .start(TimeUtil.getHour(10, 1))
                        .end(TimeUtil.getHour(11, 0))
                        .build()
        );

        Assertions.assertThrows(PaymentException.class, () -> this.paymentService.calculate(workDays));
    }

    @Test
    void shouldCalculateAnyValueWhenWorkDaysMatch() throws PaymentException {

        final List<DayWorkedDTO> workDays = Collections.singletonList(
                DayWorkedDTO.builder()
                        .day(Day.THURSDAY)
                        .start(TimeUtil.getHour(10, 1))
                        .end(TimeUtil.getHour(11, 0))
                        .build()
        );


        Mockito.when(this.paymentRepository.findByDay(Mockito.any(Day.class))).thenReturn(Payment.builder()
                .day(Day.MONDAY)
                .hourlyWages(Collections.singletonList(HourlyWage.builder()
                        .total(10)
                        .period(Period.newPeriod(TimeUtil.getHour(9, 1), TimeUtil.getHour(11, 0)))
                        .build()))
                .build());
        Assertions.assertEquals(Double.valueOf(10), this.paymentService.calculate(workDays));
        Mockito.verify(this.paymentRepository, Mockito.times(1)).findByDay(Mockito.any(Day.class));
    }


    @Test
    void shouldCalculateZeroValueWhenWorkDaysNotMatch() throws PaymentException {

        final List<DayWorkedDTO> workDays = Collections.singletonList(
                DayWorkedDTO.builder()
                        .day(Day.THURSDAY)
                        .start(TimeUtil.getHour(10, 1))
                        .end(TimeUtil.getHour(11, 0))
                        .build()
        );

        Mockito.when(this.paymentRepository.findByDay(Mockito.any(Day.class))).thenReturn(Payment.builder()
                .hourlyWages(Collections.emptyList())
                .build());
        Assertions.assertEquals(Double.valueOf(0), this.paymentService.calculate(workDays));
        Mockito.verify(this.paymentRepository, Mockito.times(1)).findByDay(Mockito.any(Day.class));
    }

}
