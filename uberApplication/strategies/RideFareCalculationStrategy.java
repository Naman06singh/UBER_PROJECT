package com.codingshuttle.project.uber.uberApplication.strategies;

import com.codingshuttle.project.uber.uberApplication.entities.RideRequest;

public interface RideFareCalculationStrategy {

    Double RIDE_FARE_MULTIPLIER = 10.0;

    double calculateFare(RideRequest rideRequest);
}
