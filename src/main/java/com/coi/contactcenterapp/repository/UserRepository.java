package com.coi.contactcenterapp.repository;

import com.coi.contactcenterapp.domain.auth.User;
import com.coi.contactcenterapp.domain.common.IntegerId;
import com.coi.contactcenterapp.repository.common.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AbstractRepository<User, IntegerId> {
}
