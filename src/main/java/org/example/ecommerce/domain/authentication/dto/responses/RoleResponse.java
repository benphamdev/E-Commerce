package org.example.ecommerce.domain.authentication.dto.responses;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class RoleResponse {
    private String name;
    private String description;
    private Set<PermissionResponse> permissions;
}
