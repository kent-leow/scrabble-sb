package com.example.scrabblesb.users;

import com.example.scrabblesb.auth.dtos.TokenPayloadDto;
import com.example.scrabblesb.auth.interceptors.AuthInterceptor;
import com.example.scrabblesb.auth.utils.JwtService;
import com.example.scrabblesb.config.WebMvcConfig;
import com.example.scrabblesb.configs.EnableMongoTestServer;
import com.example.scrabblesb.scores.ScoresRepository;
import com.example.scrabblesb.scores.ScoresService;
import com.example.scrabblesb.users.enums.Role;
import com.example.scrabblesb.users.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EnableMongoTestServer
public class UsersControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WebMvcConfig webMvcConfig;

    @Autowired
    private UsersController usersController;

    @Autowired
    private AuthInterceptor authInterceptor;

    @MockBean
    private JwtService jwtService;

    @Autowired
    private UsersService userService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ScoresService scoresService;

    @Autowired
    private ScoresRepository scoresRepository;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();
    }


    @Test
    public void shouldReturnUserWithToken() throws Exception {
        User user = User.builder()
                .username("user")
                .password("password")
                .role(Role.USER)
                .build();

        usersRepository.save(user);

        TokenPayloadDto tokenPayloadDto = TokenPayloadDto.builder()
                .username("user")
                .role(Role.USER)
                .sub("1").build();

        mockMvc.perform(get("/users/me")
                        .contentType(MediaType.APPLICATION_JSON)
                        .requestAttr("user", tokenPayloadDto))
                .andExpect(status().isOk())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("{\"username\":\"user\",\"role\":\"USER\"}"));

    }

    @Test
    public void shouldThrowBadRequestErrorWithoutToken() throws Exception {
        mockMvc.perform(get("/users/me")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnNotFoundErrorWithInvalidToken() throws Exception {
        TokenPayloadDto tokenPayloadDto = TokenPayloadDto.builder()
                .username("unknown")
                .role(Role.USER)
                .sub("1").build();

        mockMvc.perform(get("/users/me")
                        .contentType(MediaType.APPLICATION_JSON)
                        .requestAttr("user", tokenPayloadDto))
                .andExpect(status().isNotFound());
    }
}
