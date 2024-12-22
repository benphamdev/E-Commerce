package org.example.ecommerce.domain.authentication.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.ecommerce.application.mapper.PermissionMapper;
import org.example.ecommerce.domain.authentication.dto.requests.PermissionRequest;
import org.example.ecommerce.domain.authentication.dto.responses.PermissionResponse;
import org.example.ecommerce.domain.authentication.entity.Permission;
import org.example.ecommerce.domain.authentication.repositories.PermissionRepository;
import org.example.ecommerce.domain.authentication.services.IPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(
        level = AccessLevel.PRIVATE,
        makeFinal = true
)
public class PermissionService implements IPermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @Override
    public PermissionResponse createPermission(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    @Override
    public List<PermissionResponse> getAllPermissions() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    @Override
    public void deletePermission(String permission) {
        permissionRepository.deleteById(permission);
    }
}
