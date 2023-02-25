package com.coi.contactcenterapp.service.auth;

import com.coi.contactcenterapp.domain.entity.Role;
import com.coi.contactcenterapp.repository.RoleRepository;
import com.coi.contactcenterapp.service.common.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoleService implements BaseService<Role, String> {
    private final RoleRepository repository;

    @Override
    public Optional<Role> getEntityById(String stringId) {
        return repository.findById(stringId);
    }

    public Role addRole(Role role) {
        return repository.save(role);
    }
}
