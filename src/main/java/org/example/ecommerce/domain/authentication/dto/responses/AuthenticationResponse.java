package org.example.ecommerce.domain.authentication.dto.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthenticationResponse {
    private boolean authenticated;
    private String token;
}
