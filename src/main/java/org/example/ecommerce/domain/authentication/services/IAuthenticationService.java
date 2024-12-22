package org.example.ecommerce.domain.authentication.services;

import com.nimbusds.jose.JOSEException;
import org.example.ecommerce.domain.authentication.dto.requests.LoginRequest;
import org.example.ecommerce.domain.authentication.dto.requests.LogoutRequest;
import org.example.ecommerce.domain.authentication.dto.requests.RefreshTokenRequest;
import org.example.ecommerce.domain.authentication.dto.responses.AuthenticationResponse;

import java.text.ParseException;

public interface IAuthenticationService {
    AuthenticationResponse login(LoginRequest loginRequest);

    void logout(LogoutRequest token) throws ParseException, JOSEException;

    AuthenticationResponse refreshToken(
            RefreshTokenRequest token
    ) throws ParseException, JOSEException;

}
