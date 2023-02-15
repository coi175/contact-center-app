package com.coi.contactcenterapp.domain.common;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractEntity<ID extends EntityId> implements Entity, Serializable {
    private ID id;

    @Id
    @GeneratedValue
    public ID getId() {
        return id;
    }
    public void setId(ID id) {
        this.id = id;
    }
}
