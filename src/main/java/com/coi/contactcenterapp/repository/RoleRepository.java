package com.coi.contactcenterapp.repository;

import com.coi.contactcenterapp.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
