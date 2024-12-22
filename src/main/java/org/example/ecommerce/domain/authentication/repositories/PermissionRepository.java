package org.example.ecommerce.domain.authentication.repositories;

import org.example.ecommerce.domain.authentication.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
}
