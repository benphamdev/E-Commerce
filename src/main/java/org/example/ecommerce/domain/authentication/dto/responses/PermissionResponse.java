package org.example.ecommerce.domain.authentication.dto.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PermissionResponse {
    private String name;
    private String description;
}
