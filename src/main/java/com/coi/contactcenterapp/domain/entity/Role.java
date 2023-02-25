package com.coi.contactcenterapp.domain.entity;

import com.coi.contactcenterapp.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="role")
public class Role implements GrantedAuthority, BaseEntity {
    @Id
    @Column(name = "role")
    private String roleId;

    @Override
    public String getAuthority() {
        return roleId;
    }
}
