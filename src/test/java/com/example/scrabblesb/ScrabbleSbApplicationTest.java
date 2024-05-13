package com.example.scrabblesb;

import com.example.scrabblesb.auth.AuthController;
import com.example.scrabblesb.auth.AuthService;
import com.example.scrabblesb.auth.interceptors.AuthInterceptor;
import com.example.scrabblesb.auth.utils.JwtService;
import com.example.scrabblesb.config.WebMvcConfig;
import com.example.scrabblesb.configs.EnableMongoTestServer;
import com.example.scrabblesb.scores.ScoresController;
import com.example.scrabblesb.scores.ScoresRepository;
import com.example.scrabblesb.scores.ScoresService;
import com.example.scrabblesb.users.UsersController;
import com.example.scrabblesb.users.UsersRepository;
import com.example.scrabblesb.users.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnableMongoTestServer
public class ScrabbleSbApplicationTest {
    @Autowired
    MongoTemplate mongoTemplate;

    @MockBean
    WebMvcConfig webMvcConfig;

    @MockBean
    AuthController authController;

    @MockBean
    AuthService authService;

    @MockBean
    AuthInterceptor authInterceptor;

    @MockBean
    JwtService jwtService;

    @MockBean
    ScoresController scoresController;

    @MockBean
    ScoresService scoresService;

    @MockBean
    ScoresRepository scoresRepository;

    @MockBean
    UsersController usersController;

    @MockBean
    UsersService usersService;

    @MockBean
    UsersRepository usersRepository;

    @Test
    void contextLoads() {
        assertThat(mongoTemplate).isNotNull();
        assertThat(webMvcConfig).isNotNull();
        assertThat(authController).isNotNull();
        assertThat(authService).isNotNull();
        assertThat(authInterceptor).isNotNull();
        assertThat(jwtService).isNotNull();
        assertThat(scoresController).isNotNull();
        assertThat(scoresService).isNotNull();
        assertThat(scoresRepository).isNotNull();
        assertThat(usersController).isNotNull();
        assertThat(usersService).isNotNull();
        assertThat(usersRepository).isNotNull();
    }
}
