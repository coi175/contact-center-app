package com.coi.contactcenterapp.repository.common;

import com.coi.contactcenterapp.domain.common.AbstractEntity;
import com.coi.contactcenterapp.domain.common.EntityId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<ENTITY extends AbstractEntity<ID>, ID extends EntityId> extends CrudRepository<ENTITY, ID> {

}
