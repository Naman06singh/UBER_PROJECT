package com.codingshuttle.project.uber.uberApplication.strategies;

import com.codingshuttle.project.uber.uberApplication.entities.Driver;
import com.codingshuttle.project.uber.uberApplication.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {

     List<Driver> findMatchingDriver(RideRequest rideRequest);
}
