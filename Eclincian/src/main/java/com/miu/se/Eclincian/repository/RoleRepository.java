package com.miu.se.Eclincian.repository;

import com.miu.se.Eclincian.entity.Role;
import com.miu.se.Eclincian.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRole(Roles role);
}
