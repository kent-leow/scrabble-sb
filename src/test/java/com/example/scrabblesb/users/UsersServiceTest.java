package com.example.scrabblesb.users;

import com.example.scrabblesb.users.dtos.MeResponseDto;
import com.example.scrabblesb.users.enums.Role;
import com.example.scrabblesb.users.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsersServiceTest {
    @InjectMocks
    private UsersService userService;

    @Mock
    private UsersRepository usersRepository;

    @Test
    public void shouldGetUserIfExistInDb() {
        User user = User.builder()
                .id("1")
                .username("user")
                .role(Role.USER)
                .build();

        when(usersRepository.findByUsername(anyString())).thenReturn(user);

        MeResponseDto meResponseDto = userService.getMe("user");

        assertEquals(meResponseDto.getUsername(), user.getUsername());
    }

    @Test
    public void shouldThrowErrorIfUserNotExistInDb() {
        when(usersRepository.findByUsername(anyString())).thenReturn(null);

        try {
            userService.getMe("user");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("User not found"));
        }
    }
}