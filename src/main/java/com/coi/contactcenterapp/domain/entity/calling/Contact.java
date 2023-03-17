package com.coi.contactcenterapp.domain.entity.calling;

import com.coi.contactcenterapp.domain.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="contact")
public class Contact implements BaseEntity {
    @Id
    //@GeneratedValue(generator = "uuid")
    //@GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name="contact_id")
    private String contactId;
    @Column(name="phone_number", unique = true, nullable = false)
    @NonNull
    private String phoneNumber;
    @Column(name="full_name", nullable = false)
    @NonNull
    private String fullName;
    @Column(name="contact_note", nullable = false)
    @NonNull
    private String contactNote;
    @Column(name="contact_type", nullable = false)
    @NonNull
    private String contactType;
    @Column(name="contact_status", nullable = false)
    @NonNull
    private String contactStatus;
    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PhoneCall> phoneCallList;
    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Task> taskList;
}
