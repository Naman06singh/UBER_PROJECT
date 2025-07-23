package com.codingshuttle.project.uber.uberApplication.strategies;

import com.codingshuttle.project.uber.uberApplication.strategies.impl.DriverMatchingHighestRatedDriverStrategy;
import com.codingshuttle.project.uber.uberApplication.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.codingshuttle.project.uber.uberApplication.strategies.impl.RideFareSurgePricingFareCalculationStrategy;
import com.codingshuttle.project.uber.uberApplication.strategies.impl.RiderFareDefaultFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {

    private final DriverMatchingNearestDriverStrategy nearestDriverStrategy;
    private final DriverMatchingHighestRatedDriverStrategy highestRatedDriverStrategy;
    private final RideFareSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy;
    private final RiderFareDefaultFareCalculationStrategy defaultFareCalculationStrategy;

    public DriverMatchingStrategy driverMatchingStrategy(double riderRating){
        if (riderRating >= 4.8){
            return highestRatedDriverStrategy;
        }else {
            return nearestDriverStrategy;
        }
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy(){

        LocalTime surgeStartTime = LocalTime.of(18,0);
        LocalTime surgeEndTime = LocalTime.of(21,0);
        LocalTime currentTime = LocalTime.now();

        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);

        if (isSurgeTime){
            return surgePricingFareCalculationStrategy;
        } else {
            return defaultFareCalculationStrategy;
        }

    }
}
