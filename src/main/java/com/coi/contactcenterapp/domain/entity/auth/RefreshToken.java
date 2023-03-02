package com.coi.contactcenterapp.domain.entity.auth;

import com.coi.contactcenterapp.domain.common.BaseEntity;
import com.coi.contactcenterapp.domain.entity.person.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="refresh_token")
public class RefreshToken implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="refresh_toke_id")
    private Integer refreshTokenId;
    @Column(name="token", nullable = false)
    @NonNull
    private String token;
    @Column(name="username", nullable = false)
    @NonNull
    private String username;
    @Column(name = "date", nullable = false)
    @NonNull
    private LocalDateTime dateTime;
    @Column(name = "is_expired", nullable = false, columnDefinition = "bool default false")
    private boolean isExpired = false;
}
