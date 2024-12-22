package org.example.ecommerce.domain.authentication.dto.responses;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class IntrospectResponse {
    private boolean valid;
}
