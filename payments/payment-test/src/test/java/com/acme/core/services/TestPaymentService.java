package com.acme.core.services;

import com.acme.core.App;
import com.acme.core.commons.exceptions.PaymentException;
import com.acme.core.fixtures.Fixture;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class})
public class TestPaymentService {

    @Autowired
    private PaymentService paymentService;

    @Test
    public void case1() throws PaymentException {
        Assertions.assertEquals(Double.valueOf(215), this.paymentService.calculate(Fixture.CASE_1));
    }

    @Test
    public void case2() throws PaymentException {
        Assertions.assertEquals(Double.valueOf(85), this.paymentService.calculate(Fixture.CASE_2));
    }

}
