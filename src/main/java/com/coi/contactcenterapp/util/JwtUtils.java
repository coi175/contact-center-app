package com.coi.contactcenterapp.util;

import com.coi.contactcenterapp.domain.auth.JwtAuthentication;
import com.coi.contactcenterapp.domain.entity.Role;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class to generate JwtAuthentication payload
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUtils {
    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRole(claims));
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }

    private static Set<Role> getRole(Claims claims) {
        final Role role = new Role(claims.get("role", String.class));
        Set<Role> set = new HashSet<>();
        set.add(role);
        return set;
    }
}
