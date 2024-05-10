package com.example.scrabblesb.users.dtos;

import com.example.scrabblesb.users.constants.RegexConstants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = RegexConstants.PASSWORD_REGEX, message = "Password is too weak")
    private String password;
}
