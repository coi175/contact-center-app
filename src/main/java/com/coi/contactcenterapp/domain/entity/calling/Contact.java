package com.coi.contactcenterapp.domain.entity.calling;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name="contact")
public class Contact {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name="contact_id")
    private String contact_id;
    @Column(name="phone_number", unique = true, nullable = false)
    @NonNull
    private String phoneNumber;
    @Column(name="full_name", nullable = false)
    @NonNull
    private String fullName;
    @Column(name="contact_note", nullable = false)
    @NonNull
    private String contactNote;
    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PhoneCall> phoneCallList;
    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Task> taskList;
}
