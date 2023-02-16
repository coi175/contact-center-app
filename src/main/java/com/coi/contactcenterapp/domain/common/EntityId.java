package com.coi.contactcenterapp.domain.common;

import java.io.Serializable;

/**
 * EntityId provide interface to work with different types of ids (stringId, integerId etc.) in one place
 * @param <V>
 */
public interface EntityId<V> extends Serializable {
    public <V> V getValue();
    public void setValue(V value);
}
