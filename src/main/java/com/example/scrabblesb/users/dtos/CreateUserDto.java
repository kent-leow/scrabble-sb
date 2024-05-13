package com.example.scrabblesb.users.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDto {
    @NotEmpty
    private String username;

    @NotEmpty
//    @Pattern(regexp = RegexConstants.PASSWORD_REGEX, message = "Password is too weak")
    private String password;
}
