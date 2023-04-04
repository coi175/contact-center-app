package com.coi.contactcenterapp.domain.entity.calling;

import com.coi.contactcenterapp.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="report")
public class Report implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;
    // дата репорта
    // оператор
    // менеджер
}
