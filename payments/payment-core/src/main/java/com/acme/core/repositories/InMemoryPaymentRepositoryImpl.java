package com.acme.core.repositories;

import com.acme.core.domains.Payment;
import com.acme.core.domains.fixtures.FixturePayment;
import com.acme.core.domains.types.Day;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class InMemoryPaymentRepositoryImpl implements PaymentRepository {

    private final Set<Payment> payments;

    public InMemoryPaymentRepositoryImpl() {
        this.payments = FixturePayment.PAYMENTS;
    }

    @Override
    public Payment findByDay(Day day) {
        return this.payments
                .stream()
                .filter(payment -> payment.getDay().equals(day))
                .findFirst()
                .orElse(Payment.builder().build());
    }
}
