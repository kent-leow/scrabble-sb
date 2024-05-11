package com.example.scrabblesb.users;

import com.example.scrabblesb.auth.interceptors.AuthInterceptor;
import com.example.scrabblesb.auth.utils.JwtService;
import com.example.scrabblesb.users.dtos.MeResponseDto;
import com.example.scrabblesb.users.enums.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsersController.class)
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthInterceptor authInterceptor;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UsersService userService;

    @Test
    public void shouldReturnUser() throws Exception {
        // mock authInterceptor to return true
        MeResponseDto response = MeResponseDto.builder()
                .id("1")
                .username("test")
                .role(Role.USER).build();

        given(userService.getMe("test")).willReturn(response);

        mockMvc.perform(get("/users/me")
                        .header("Authorization", "Bearer token"))
                .andExpect(status().isOk());
    }
}