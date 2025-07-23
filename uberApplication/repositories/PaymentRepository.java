package com.codingshuttle.project.uber.uberApplication.repositories;

import com.codingshuttle.project.uber.uberApplication.entities.Payment;
import com.codingshuttle.project.uber.uberApplication.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByRide(Ride ride);
}
