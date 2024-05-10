package com.example.scrabblesb.users.dtos;

import com.example.scrabblesb.users.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeResponseDto {
    private String id;

    private String username;

    private Role role;
}
