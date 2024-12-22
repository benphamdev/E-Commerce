package org.example.ecommerce.domain.authentication.repositories;

import org.example.ecommerce.domain.authentication.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
