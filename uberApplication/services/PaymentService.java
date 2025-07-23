package com.codingshuttle.project.uber.uberApplication.services;

import com.codingshuttle.project.uber.uberApplication.entities.Payment;
import com.codingshuttle.project.uber.uberApplication.entities.Ride;
import com.codingshuttle.project.uber.uberApplication.entities.enums.PaymentStatus;

public interface PaymentService {

    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus status);
}
