package com.coi.contactcenterapp.service.common;

import com.coi.contactcenterapp.domain.common.AbstractEntity;
import com.coi.contactcenterapp.domain.common.EntityId;
import com.coi.contactcenterapp.repository.common.AbstractRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractService<ENTITY extends AbstractEntity<ID>, ID extends EntityId,
        R extends AbstractRepository<ENTITY, ID>> implements BaseService<ENTITY, ID> {
    protected final R repository;

    // методы
}
