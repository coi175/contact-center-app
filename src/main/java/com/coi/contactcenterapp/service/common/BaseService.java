package com.coi.contactcenterapp.service.common;

import com.coi.contactcenterapp.domain.common.AbstractEntity;
import com.coi.contactcenterapp.domain.common.EntityId;

public interface BaseService<ENTITY extends AbstractEntity<ID>, ID extends EntityId> {
    public ENTITY read(ID id);
    public ENTITY add(ENTITY entity);
    public ENTITY update(ENTITY entity);
    public <T> T delete(ID id);
}
