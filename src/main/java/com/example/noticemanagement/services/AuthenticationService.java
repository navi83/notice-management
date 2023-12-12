package com.example.noticemanagement.services;

import com.example.noticemanagement.dtos.requests.AuthenticationRequest;
import com.example.noticemanagement.dtos.responses.AuthenticationResponse;
import com.example.noticemanagement.dtos.requests.RegisterRequest;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
