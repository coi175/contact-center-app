package com.coi.contactcenterapp.domain.entity.person;

import com.coi.contactcenterapp.domain.common.BaseEntity;
import com.coi.contactcenterapp.domain.entity.calling.Task;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="manager")
public class Manager implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="manager_id")
    private Integer managerId;
    @OneToOne(mappedBy = "manager", cascade = CascadeType.ALL, optional = false)
    @NonNull
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;
    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Operator> operators;
    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Task> taskList;
}
