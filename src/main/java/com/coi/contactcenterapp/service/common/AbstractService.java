package com.coi.contactcenterapp.service.common;

import com.coi.contactcenterapp.domain.common.AbstractEntity;
import com.coi.contactcenterapp.domain.common.EntityId;
import com.coi.contactcenterapp.repository.common.AbstractRepository;
import lombok.RequiredArgsConstructor;

/**
 * Abstract Service that work with abstract repository and realize main methods for all services
 * @param <ENTITY>
 * @param <ID>
 * @param <R>
 */
@RequiredArgsConstructor
public abstract class AbstractService<ENTITY extends AbstractEntity<ID>, ID extends EntityId,
        R extends AbstractRepository<ENTITY, ID>> implements BaseService<ENTITY, ID> {
    protected final R repository;

    @Override
    public ENTITY read(ID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ID add(ENTITY entity) {
        return entity.getId();
    }

    @Override
    public ENTITY update(ENTITY entity) {
        return entity;
    }

    @Override
    public ID delete(ID id) {
        return id;
    }
}
