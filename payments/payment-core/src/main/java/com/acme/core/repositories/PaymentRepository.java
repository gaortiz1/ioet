package com.acme.core.repositories;

import com.acme.core.domains.Payment;
import com.acme.core.domains.types.Day;

public interface PaymentRepository {

    Payment findByDay(Day day);
}
