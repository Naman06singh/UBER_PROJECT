package com.codingshuttle.project.uber.uberApplication.services;

import com.codingshuttle.project.uber.uberApplication.dto.DriverDto;
import com.codingshuttle.project.uber.uberApplication.dto.SignupDto;
import com.codingshuttle.project.uber.uberApplication.dto.UserDto;

public interface AuthService {

    String[] login(String email, String password);

    UserDto signup(SignupDto signupDto);

    DriverDto onboardNewDriver(Long userId, String vehicleId);

    String refreshToken(String refreshToken);
}
