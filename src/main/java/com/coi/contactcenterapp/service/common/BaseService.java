package com.coi.contactcenterapp.service.common;

import com.coi.contactcenterapp.domain.common.BaseEntity;

import java.util.Optional;

/**
 * Base service interface that describe main methods (crud) for all services
 */
public interface BaseService<ENTITY extends BaseEntity, ID> {
     Optional<ENTITY> getEntityById(ID id);
}
