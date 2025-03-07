package com.projetmanager.promanager.service;

import com.projetmanager.promanager.controller.AuthenticationRequest;
import com.projetmanager.promanager.controller.AuthenticationResponse;
import com.projetmanager.promanager.controller.RegisterRequest;
import com.projetmanager.promanager.entity.User;
import com.projetmanager.promanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Autowired
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;

    public AuthService(PasswordEncoder passwordEncoder, JwtService jwtService, UserRepository repository, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.repository = repository;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request){
        var user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        repository.save(user);

        var jwtToken = jwtService.generateToken(user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtToken);
        return response;
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtToken);
        return response;

    }
}
