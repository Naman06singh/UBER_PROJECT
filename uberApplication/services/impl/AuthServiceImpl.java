package com.codingshuttle.project.uber.uberApplication.services.impl;

import com.codingshuttle.project.uber.uberApplication.dto.DriverDto;
import com.codingshuttle.project.uber.uberApplication.dto.SignupDto;
import com.codingshuttle.project.uber.uberApplication.dto.UserDto;
import com.codingshuttle.project.uber.uberApplication.entities.Driver;
import com.codingshuttle.project.uber.uberApplication.entities.User;
import com.codingshuttle.project.uber.uberApplication.entities.enums.Role;
import com.codingshuttle.project.uber.uberApplication.exceptions.ResourceNotFoundException;
import com.codingshuttle.project.uber.uberApplication.exceptions.RuntimeConflictException;
import com.codingshuttle.project.uber.uberApplication.repositories.UserRepository;
import com.codingshuttle.project.uber.uberApplication.security.JWTService;
import com.codingshuttle.project.uber.uberApplication.services.AuthService;
import com.codingshuttle.project.uber.uberApplication.services.DriverService;
import com.codingshuttle.project.uber.uberApplication.services.RiderService;
import com.codingshuttle.project.uber.uberApplication.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public String[] login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        User user = (User) authentication.getPrincipal();

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new String[]{accessToken, refreshToken};

    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
        User user = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if (user != null)
            throw new RuntimeConflictException("Cannot signup, User already exists with email "+signupDto.getEmail());

        User mappedUser = modelMapper.map(signupDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        User savedUser = userRepository.save(mappedUser);

        riderService.createNewRider(savedUser);
        walletService.createNewWallet(savedUser);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId, String vehicleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id "+ userId));
        if (user.getRoles().contains(Role.DRIVER))
            throw new RuntimeConflictException("User with id "+userId+"is already a Driver");

        Driver createDriver = Driver.builder()
                .user(user)
                .rating(0.0)
                .vehicleId(vehicleId)
                .available(true)
                .build();
        user.getRoles().add(Role.DRIVER);
        userRepository.save(user);
        Driver savedDriver = driverService.createNewDriver(createDriver);
        return modelMapper.map(savedDriver, DriverDto.class);
    }

    @Override
    public String refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found " +
                "with id: "+userId));

        return jwtService.generateAccessToken(user);
    }
}
