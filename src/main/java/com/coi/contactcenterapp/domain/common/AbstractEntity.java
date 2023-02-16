package com.coi.contactcenterapp.domain.common;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

/**
 * Abstract Entity contains common functionality for all entities such as IDs
 * @param <ID>
 */
@MappedSuperclass
public abstract class AbstractEntity<ID extends EntityId> implements Serializable {
    @Id
    private ID id;

    public ID getId() {
        return id;
    }
    public void setId(ID id) {
        this.id = id;
    }
}
