package com.coi.contactcenterapp.domain.entity.person;

import com.coi.contactcenterapp.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="director")
public class Director implements BaseEntity {
    @Id
    @Column(name="director_id")
    private Integer directorId;
    @OneToOne(mappedBy = "director", cascade = CascadeType.ALL, optional = false)
    @NonNull
    private Employee employee;
    @OneToMany(mappedBy = "director", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    private List<Manager> managers;
}
