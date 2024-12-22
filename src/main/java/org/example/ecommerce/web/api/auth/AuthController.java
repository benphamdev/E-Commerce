package org.example.ecommerce.web.api.auth;

import com.nimbusds.jose.JOSEException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ecommerce.contract.abstractions.shared.response.BaseResponse;
import org.example.ecommerce.domain.authentication.dto.requests.IntroSpectRequest;
import org.example.ecommerce.domain.authentication.dto.requests.LoginRequest;
import org.example.ecommerce.domain.authentication.dto.requests.LogoutRequest;
import org.example.ecommerce.domain.authentication.dto.requests.RefreshTokenRequest;
import org.example.ecommerce.domain.authentication.dto.responses.AuthenticationResponse;
import org.example.ecommerce.domain.authentication.dto.responses.IntrospectResponse;
import org.example.ecommerce.domain.authentication.services.impl.AuthenticationImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(
        makeFinal = true,
        level = lombok.AccessLevel.PRIVATE
)
public class AuthController {
    AuthenticationImpl authenticationService;

    @Operation(
            summary = "Login",
            description = "Login to the system"
    )
    @PostMapping("/login")
    BaseResponse<AuthenticationResponse> authenticate(
            @RequestBody @Valid LoginRequest request
    ) {
        var res = authenticationService.login(request);
        return BaseResponse.<AuthenticationResponse>builder()
                           .message("Login success")
                           .data(res)
                           .build();
    }

    @PostMapping("/introspect")
    BaseResponse<IntrospectResponse> authenticate(
            @RequestBody @Valid IntroSpectRequest request
    ) throws ParseException, JOSEException {
        var introspectResponse = authenticationService.introspect(request);
        return BaseResponse.<IntrospectResponse>builder()
                           .data(introspectResponse)
                           .build();
    }

    @PostMapping("/logout")
    BaseResponse<Void> logout(
            @RequestBody @Valid LogoutRequest logoutRequest
    ) throws ParseException, JOSEException {
        authenticationService.logout(logoutRequest);
        return BaseResponse.<Void>builder()
                           .message("Logout success")
                           .build();
    }

    @PostMapping("/refresh")
    BaseResponse<AuthenticationResponse> refreshToken(
            @RequestBody @Valid RefreshTokenRequest refreshToken
    ) throws ParseException, JOSEException {
        var res = authenticationService.refreshToken(refreshToken);
        return BaseResponse.<AuthenticationResponse>builder()
                           .data(res)
                           .build();
    }
}
