package org.example.ecommerce.application.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.ecommerce.contract.abstractions.shared.response.ErrorResponse;
import org.example.ecommerce.domain.common.constants.EnumsErrorResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.Date;

@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(
            HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {
        EnumsErrorResponse enumsErrorResponse = EnumsErrorResponse.UNAUTHORIZED;
        log.warn("Unauthorized error: {}", authException.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ErrorResponse errorResponse = new ErrorResponse(
                new Date(),
                enumsErrorResponse.getCode(),
                request.getServletPath(),
                enumsErrorResponse.getMessage(),
                authException.getMessage()
        );
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), errorResponse);
    }
}


