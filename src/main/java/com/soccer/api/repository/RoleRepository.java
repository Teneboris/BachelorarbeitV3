package com.soccer.api.repository;


import com.soccer.api.models.ERole;
import com.soccer.api.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
