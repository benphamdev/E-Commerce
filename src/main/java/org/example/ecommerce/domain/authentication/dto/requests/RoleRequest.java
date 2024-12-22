package org.example.ecommerce.domain.authentication.dto.requests;

import lombok.Getter;

import java.io.Serializable;
import java.util.Set;

@Getter
public class RoleRequest implements Serializable {
    private String name;
    private String description;
    private Set<String> permissions;
}
