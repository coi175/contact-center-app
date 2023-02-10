package com.coi.contactcenterapp.repository;

import com.coi.contactcenterapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
