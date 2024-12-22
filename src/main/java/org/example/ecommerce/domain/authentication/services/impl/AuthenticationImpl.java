package org.example.ecommerce.domain.authentication.services.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.example.ecommerce.domain.authentication.dto.requests.IntroSpectRequest;
import org.example.ecommerce.domain.authentication.dto.requests.LoginRequest;
import org.example.ecommerce.domain.authentication.dto.requests.LogoutRequest;
import org.example.ecommerce.domain.authentication.dto.requests.RefreshTokenRequest;
import org.example.ecommerce.domain.authentication.dto.responses.AuthenticationResponse;
import org.example.ecommerce.domain.authentication.dto.responses.IntrospectResponse;
import org.example.ecommerce.domain.authentication.entity.InvalidatedToken;
import org.example.ecommerce.domain.authentication.repositories.InvalidatedTokenRepository;
import org.example.ecommerce.domain.authentication.services.IAuthenticationService;
import org.example.ecommerce.domain.common.constants.EnumsErrorResponse;
import org.example.ecommerce.domain.common.exceptions.AppException;
import org.example.ecommerce.domain.users.entities.User;
import org.example.ecommerce.domain.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(
        level = PRIVATE,
        makeFinal = true
)
@Slf4j
public class AuthenticationImpl implements IAuthenticationService {
    UserRepository userRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;
    @NonFinal
    @Value("${jwt.secret}")
    String SECRET_KEY;

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        var user = userRepository.findByEmail(loginRequest.getEmail())
                                 .orElseThrow(() -> new RuntimeException("User not found"));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword()
        );

        if (!authenticated) {
            throw new RuntimeException("Unauthorized");
        }

        var token = generateToken(user);

        return AuthenticationResponse.builder()
                                     .token(token)
                                     .authenticated(true)
                                     .build();
    }

    private String generateToken(User user) {
        JWSHeader jwtHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issuer("thejavabanking")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now()
                                                .plus(1, ChronoUnit.HOURS)
                                                .toEpochMilli()))
                .jwtID(UUID.randomUUID()
                           .toString())
                .claim("scope", buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwtHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token ", e);
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles()))
            user.getRoles()
                .forEach(role -> {
                    stringJoiner.add("ROLE_" + role.getName());
                    if (!CollectionUtils.isEmpty(role.getPermissions()))
                        role.getPermissions()
                            .forEach(permission -> stringJoiner.add(permission.getName()));

                });

        return stringJoiner.toString();
    }

    public IntrospectResponse introspect(IntroSpectRequest request) {
        // Implement introspect method here
        var token = request.getToken();
        boolean isValid = true;
        try {
            verifyToken(token);
        } catch (Exception e) {
            isValid = false;
        }
        return IntrospectResponse.builder()
                                 .valid(isValid)
                                 .build();
    }

    private SignedJWT verifyToken(String token) throws ParseException, JOSEException {
        JWSVerifier verifier = new MACVerifier(SECRET_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        var res = signedJWT.verify(verifier);
        Date expirationTime = signedJWT.getJWTClaimsSet()
                                       .getExpirationTime();
        if (!(expirationTime.after(new Date()) && res))
            throw new AppException(EnumsErrorResponse.UNAUTHENTICATED);
        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet()
                                                           .getJWTID()))
            throw new AppException(EnumsErrorResponse.UNAUTHENTICATED);
        return signedJWT;
    }

    @Override
    public void logout(LogoutRequest logoutRequest) throws ParseException, JOSEException {
        var signToken = verifyToken(logoutRequest.getToken());
        String jit = signToken.getJWTClaimsSet()
                              .getJWTID();
        Date expirationTime = signToken.getJWTClaimsSet()
                                       .getExpirationTime();
        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
//                                                            .id(jit)
                                                            .expirationTime(expirationTime)
                                                            .build();
        invalidatedTokenRepository.save(invalidatedToken);
    }

    @Override
    public AuthenticationResponse refreshToken(
            RefreshTokenRequest token
    ) throws ParseException, JOSEException {
        var signToken = verifyToken(token.getToken());
        var jit = signToken.getJWTClaimsSet()
                           .getJWTID();
        var expirationTime = signToken.getJWTClaimsSet()
                                      .getExpirationTime();
        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                                                            .id(jit)
                                                            .expirationTime(expirationTime)
                                                            .build();
        var user = userRepository.findByEmail(signToken.getJWTClaimsSet()
                                                       .getSubject())
                                 .orElseThrow(() -> new AppException(EnumsErrorResponse.USER_NOT_FOUND));

        return AuthenticationResponse.builder()
                                     .token(generateToken(user))
                                     .authenticated(true)
                                     .build();
    }
}
