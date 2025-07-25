package com.codingshuttle.project.uber.uberApplication.dto;

import com.codingshuttle.project.uber.uberApplication.entities.enums.PaymentMethod;
import com.codingshuttle.project.uber.uberApplication.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDto {


    private Long id;

    private PointDto pickupLocation;
    private PointDto dropOffLocation;
    private LocalDateTime requestedTime;

    private RiderDto rider;
    private Double fare;


    private PaymentMethod paymentMethod;

    private RideRequestStatus rideRequestStatus;

}
