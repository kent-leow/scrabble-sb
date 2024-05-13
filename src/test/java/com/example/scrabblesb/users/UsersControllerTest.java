package com.example.scrabblesb.users;

import com.example.scrabblesb.auth.dtos.TokenPayloadDto;
import com.example.scrabblesb.auth.interceptors.AuthInterceptor;
import com.example.scrabblesb.auth.utils.JwtService;
import com.example.scrabblesb.users.dtos.MeResponseDto;
import com.example.scrabblesb.users.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsersController.class)
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsersController usersController;

    @MockBean
    private AuthInterceptor authInterceptor;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UsersService userService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();
    }


    @Test
    public void shouldReturnUserWithToken() throws Exception {
        TokenPayloadDto tokenPayloadDto = TokenPayloadDto.builder()
                .username("user")
                .role(Role.USER)
                .sub("1").build();

        MeResponseDto meResponseDto = MeResponseDto.builder()
                .id("1")
                .username("user")
                .role(Role.USER)
                .build();

        when(userService.getMe("user")).thenReturn(meResponseDto);

        mockMvc.perform(get("/users/me")
                        .contentType(MediaType.APPLICATION_JSON)
                        .requestAttr("user", tokenPayloadDto))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("{\"id\":\"1\",\"username\":\"user\",\"role\":\"USER\"}"));

    }

    @Test
    public void shouldReturnBadRequestErrorWithoutToken() throws Exception {
        mockMvc.perform(get("/users/me")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnNotFoundErrorWithInvalidToken() throws Exception {
        TokenPayloadDto tokenPayloadDto = TokenPayloadDto.builder()
                .username("unknown")
                .role(Role.USER)
                .sub("1").build();

        when(userService.getMe(anyString()))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        mockMvc.perform(get("/users/me")
                        .contentType(MediaType.APPLICATION_JSON)
                        .requestAttr("user", tokenPayloadDto))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}