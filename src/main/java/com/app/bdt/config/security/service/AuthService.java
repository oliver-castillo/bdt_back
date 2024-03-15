package com.app.bdt.config.security.service;

import com.app.bdt.config.security.JwtTokenProvider;
import com.app.bdt.exceptions.NotFoundException;
import com.app.bdt.model.entity.User;
import com.app.bdt.model.request.LoginRequest;
import com.app.bdt.model.response.LoginResponse;
import com.app.bdt.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtProvider;

    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        User user = userRepository.findUserByUsername(loginRequest.getUsername()).orElseThrow(() -> new NotFoundException("User not found"));
        UserDetails userDetails = user;

        String token = jwtProvider.getToken(userDetails);

        return LoginResponse.builder()
                .token(token)
                .build();
    }
}
