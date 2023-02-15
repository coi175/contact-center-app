package com.coi.contactcenterapp.domain.auth;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtResponse {
    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;
}
