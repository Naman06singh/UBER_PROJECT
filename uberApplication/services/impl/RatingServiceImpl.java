package com.codingshuttle.project.uber.uberApplication.services.impl;

import com.codingshuttle.project.uber.uberApplication.dto.DriverDto;
import com.codingshuttle.project.uber.uberApplication.dto.RiderDto;
import com.codingshuttle.project.uber.uberApplication.entities.Driver;
import com.codingshuttle.project.uber.uberApplication.entities.Rating;
import com.codingshuttle.project.uber.uberApplication.entities.Ride;
import com.codingshuttle.project.uber.uberApplication.entities.Rider;
import com.codingshuttle.project.uber.uberApplication.exceptions.ResourceNotFoundException;
import com.codingshuttle.project.uber.uberApplication.exceptions.RuntimeConflictException;
import com.codingshuttle.project.uber.uberApplication.repositories.DriverRepository;
import com.codingshuttle.project.uber.uberApplication.repositories.RatingRepository;
import com.codingshuttle.project.uber.uberApplication.repositories.RiderRepository;
import com.codingshuttle.project.uber.uberApplication.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    
    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper modelMapper;

    @Override
    public DriverDto rateDriver(Ride ride, Integer rating) {
        Driver driver = ride.getDriver();
        Rating ratingObj = ratingRepository.findByRide(ride)
                .orElseThrow(()-> new ResourceNotFoundException("Rating not found for ride with id "+ride.getId()));

        if (ratingObj.getDriverRating() != null)
            throw new RuntimeConflictException("Driver has already been rated, cannot rate again");

        ratingObj.setDriverRating(rating);

        ratingRepository.save(ratingObj);

        Double newRating = ratingRepository.findByDriver(driver)
                .stream()
                .mapToDouble(Rating::getDriverRating)
                .average().orElse(0.0);
        driver.setRating(newRating);

        Driver savedDriver = driverRepository.save(driver);
        return modelMapper.map(savedDriver, DriverDto.class);

    }

    @Override
    public RiderDto rateRider(Ride ride, Integer rating) {
        Rider rider = ride.getRider();
        Rating ratingObj = ratingRepository.findByRide(ride)
                .orElseThrow(()-> new ResourceNotFoundException("Rating not found for ride with id "+ride.getId()));
        if (ratingObj.getRiderRating() != null)
            throw new RuntimeConflictException("Rider has already been rated, cannot rate again");

        ratingObj.setRiderRating(rating);

        ratingRepository.save(ratingObj);

        Double newRating = ratingRepository.findByRider(rider)
                .stream()
                .mapToDouble(Rating::getRiderRating)
                .average().orElse(0.0);
        rider.setRating(newRating);

        Rider savedRider = riderRepository.save(rider);
        return modelMapper.map(savedRider, RiderDto.class);
    }

    @Override
    public void crreateNewRating(Ride ride) {
        Rating rating = Rating.builder()
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .ride(ride)
                .build();
        ratingRepository.save(rating);
    }
}
