package com.coi.contactcenterapp.domain.auth;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    private String token;
    private User user;
    private Date date;
}
