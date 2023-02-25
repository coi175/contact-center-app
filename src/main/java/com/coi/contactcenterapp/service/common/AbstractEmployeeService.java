package com.coi.contactcenterapp.service.common;

import com.coi.contactcenterapp.domain.common.BaseEntity;

import java.util.Optional;

public class AbstractEmployeeService<ENTITY extends BaseEntity, ID> implements BaseService<ENTITY, ID> {
    @Override
    public Optional<ENTITY> getEntityById(ID id) {
        return null;
    }
}
