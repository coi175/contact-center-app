package com.coi.contactcenterapp.repository;

import com.coi.contactcenterapp.domain.entity.auth.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    @Query("SELECT r FROM RefreshToken r WHERE r.isExpired = false and r.username = :username")
    Optional<RefreshToken> findRefreshTokenByUsername(@Param("username") String username);

    @Query("SELECT r FROM RefreshToken r WHERE r.isExpired = false and r.token = :token")
    Optional<RefreshToken> findRefreshTokenByToken(@Param("token") String token);

    RefreshToken deleteAllByUsername(String username);
}
