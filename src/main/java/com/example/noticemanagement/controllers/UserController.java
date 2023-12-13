package com.example.noticemanagement.controllers;

import com.example.noticemanagement.dtos.requests.AuthenticationRequest;
import com.example.noticemanagement.dtos.requests.RegisterRequest;
import com.example.noticemanagement.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
@Tag(name = "User Controller")
public class UserController {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final AuthenticationService service;

    @Operation(summary = "Create account")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        LOGGER.info("Process register account");
        return ResponseEntity.ok(service.register(request));
    }

    @Operation(summary = "Log in")
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        LOGGER.info("Process authenticate account");
        return ResponseEntity.ok(service.authenticate(request));
    }

}
