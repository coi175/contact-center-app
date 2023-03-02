package com.coi.contactcenterapp.service.common;

import com.coi.contactcenterapp.domain.common.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class AbstractEmployeeService<ENTITY extends BaseEntity, ID, Repository extends JpaRepository<ENTITY, ID>>
        implements BaseService<ENTITY, ID> {
    Repository repository;
    @Override
    public Optional<ENTITY> getEntityById(ID id) {
        return repository.findById(id);
    }
}
