package org.example.ecommerce.domain.authentication.services;

import org.example.ecommerce.domain.authentication.dto.requests.PermissionRequest;
import org.example.ecommerce.domain.authentication.dto.responses.PermissionResponse;

import java.util.List;

public interface IPermissionService {
    PermissionResponse createPermission(PermissionRequest request);

    List<PermissionResponse> getAllPermissions();

    void deletePermission(String permission);
}
