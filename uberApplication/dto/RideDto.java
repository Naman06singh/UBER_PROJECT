package com.codingshuttle.project.uber.uberApplication.dto;

import com.codingshuttle.project.uber.uberApplication.entities.enums.PaymentMethod;
import com.codingshuttle.project.uber.uberApplication.entities.enums.RideStatus;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
public class RideDto {


    private Long id;
    private PointDto pickupLocation;
    private PointDto dropOffLocation;

    private LocalDateTime createdTime;
    private RiderDto rider;
    private DriverDto driver;
    private PaymentMethod paymentMethod;

    private RideStatus rideStatus;

    private String otp;

    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
