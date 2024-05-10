package com.example.scrabblesb.auth.dtos;

import com.example.scrabblesb.users.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenPayloadDto {
    private String sub;

    private String username;

    private Role role;
}
