package com.codingshuttle.project.uber.uberApplication.services;

import com.codingshuttle.project.uber.uberApplication.dto.DriverDto;
import com.codingshuttle.project.uber.uberApplication.dto.RiderDto;
import com.codingshuttle.project.uber.uberApplication.entities.Driver;
import com.codingshuttle.project.uber.uberApplication.entities.Ride;
import com.codingshuttle.project.uber.uberApplication.entities.Rider;

public interface RatingService {

    DriverDto rateDriver(Ride ride, Integer rating);
    RiderDto rateRider(Ride ride, Integer rating);

    void crreateNewRating(Ride ride);


}
