package com.coi.contactcenterapp.service.auth;

import com.coi.contactcenterapp.domain.entity.auth.RefreshToken;
import com.coi.contactcenterapp.domain.entity.person.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
@Slf4j
/**
 * Class that provide and validate jwt tokens
 */
public class JwtProvider {
    private final SecretKey jwtAccessSecret;
    private final SecretKey jwtRefreshSecret;
    private final Integer accessTimeToExpInMinutes;
    private final Integer refreshTimeExpInDays;
    private final RefreshTokenService refreshTokenService;

    public JwtProvider(@Value("${jwt.secret.access}") String jwtAccessSecret,
                       @Value("${jwt.secret.refresh}") String jwtRefreshSecret,
                       @Value("${jwt.secret.accessTimeToExpInMinutes}") Integer accessTimeToExpInMinutes,
                       @Value("${jwt.secret.refreshTimeExpInDays}") Integer refreshTimeExpInDays,
                       RefreshTokenService refreshTokenService)
    {
        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret));
        this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRefreshSecret));
        this.accessTimeToExpInMinutes = accessTimeToExpInMinutes;
        this.refreshTimeExpInDays = refreshTimeExpInDays;
        this.refreshTokenService = refreshTokenService;
    }

    /**
     * Method that generates new access token
     * @param user
     * @return
     */
    public String generateAccessToken(@NonNull User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(getExpTimeForRefreshTokenDate())
                .signWith(jwtAccessSecret)
                .claim("role", user.getRole().getAuthority())
                .compact();
    }

    /**
     * Method that generates new refresh token
     */
    public String generateRefreshToken(@NonNull User user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant refreshExpirationInstant = now.plusDays(refreshTimeExpInDays).atZone(ZoneId.systemDefault()).toInstant();
        final Date refreshExpiration = Date.from(refreshExpirationInstant);
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(refreshExpiration)
                .signWith(jwtRefreshSecret)
                .compact();
    }

    public boolean validateAccessToken(@NonNull String accessToken) {
        return validateToken(accessToken, jwtAccessSecret, false);
    }

    public boolean validateRefreshToken(@NonNull String refreshToken) {
        return validateToken(refreshToken, jwtRefreshSecret, true);
    }

    private boolean validateToken(@NonNull String token, @NonNull Key secret, @NonNull Boolean isRefresh) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.error("Token expired", expEx);
            if (isRefresh) {
                Optional<RefreshToken> refreshToken = refreshTokenService.getTokenByToken(token);
                refreshToken.ifPresent(t -> {
                    t.setExpired(true);
                    refreshTokenService.addEntity(t);
                });
            }
        } catch (UnsupportedJwtException unsEx) {
            log.error("Unsupported jwt", unsEx);
        } catch (MalformedJwtException mjEx) {
            log.error("Malformed jwt", mjEx);
        } catch (SignatureException sEx) {
            log.error("Invalid signature", sEx);
        } catch (Exception e) {
            log.error("invalid token", e);
        }
        return false;
    }

    public Claims getAccessClaims(@NonNull String token) {
        return getClaims(token, jwtAccessSecret);
    }

    public Claims getRefreshClaims(@NonNull String token) {
        return getClaims(token, jwtRefreshSecret);
    }

    private Claims getClaims(@NonNull String token, @NonNull Key secret) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Date getExpTimeForRefreshTokenDate() {
        final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant = now.plusMinutes(accessTimeToExpInMinutes).atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(accessExpirationInstant);
    }
}
