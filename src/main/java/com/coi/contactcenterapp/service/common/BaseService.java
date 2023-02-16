package com.coi.contactcenterapp.service.common;

import com.coi.contactcenterapp.domain.common.AbstractEntity;
import com.coi.contactcenterapp.domain.common.EntityId;

/**
 * Base service interface that describe main methods (crud) for all services
 * @param <ENTITY>
 * @param <ID>
 */
public interface BaseService<ENTITY extends AbstractEntity<ID>, ID extends EntityId> {
    public ENTITY read(ID id);
    public ID add(ENTITY entity);
    public ENTITY update(ENTITY entity);
    public ID delete(ID id);
}
