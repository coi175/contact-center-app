package com.coi.contactcenterapp.domain.auth;

import com.coi.contactcenterapp.domain.Employee;
import com.coi.contactcenterapp.domain.common.AbstractEntity;
import com.coi.contactcenterapp.domain.common.IntegerId;
import com.coi.contactcenterapp.domain.common.StringId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name="user_data")
public class User extends AbstractEntity<IntegerId> {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Set<Role> role;
    @OneToOne
    private Employee employee;
}
