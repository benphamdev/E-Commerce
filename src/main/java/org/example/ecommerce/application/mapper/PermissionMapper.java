package org.example.ecommerce.application.mapper;

import org.example.ecommerce.domain.authentication.dto.requests.PermissionRequest;
import org.example.ecommerce.domain.authentication.dto.responses.PermissionResponse;
import org.example.ecommerce.domain.authentication.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
