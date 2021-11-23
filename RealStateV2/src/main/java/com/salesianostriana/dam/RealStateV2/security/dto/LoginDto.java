package com.salesianostriana.dam.RealStateV2.security.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {
    private String email;
    private String password;
}
