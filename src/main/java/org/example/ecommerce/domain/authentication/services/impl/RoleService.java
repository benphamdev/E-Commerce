package org.example.ecommerce.domain.authentication.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ecommerce.application.mapper.RoleMapper;
import org.example.ecommerce.domain.authentication.dto.requests.RoleRequest;
import org.example.ecommerce.domain.authentication.dto.responses.RoleResponse;
import org.example.ecommerce.domain.authentication.repositories.PermissionRepository;
import org.example.ecommerce.domain.authentication.repositories.RoleRepository;
import org.example.ecommerce.domain.authentication.services.IRoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(
        level = PRIVATE,
        makeFinal = true
)
public class RoleService implements IRoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    @Override
    public RoleResponse create(RoleRequest request) {

        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    @Override
    public List<RoleResponse> findAll() {
        return roleRepository.findAll()
                             .stream()
                             .map(roleMapper::toRoleResponse)
                             .toList();
    }

    @Override
    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
