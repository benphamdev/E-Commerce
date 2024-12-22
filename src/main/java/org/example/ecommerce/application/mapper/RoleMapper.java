package org.example.ecommerce.application.mapper;

import org.example.ecommerce.domain.authentication.dto.requests.RoleRequest;
import org.example.ecommerce.domain.authentication.dto.responses.RoleResponse;
import org.example.ecommerce.domain.authentication.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(
            target = "permissions",
            ignore = true
    )
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
