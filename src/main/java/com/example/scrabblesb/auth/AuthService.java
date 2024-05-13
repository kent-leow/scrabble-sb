package com.example.scrabblesb.auth;

import com.example.scrabblesb.auth.dtos.AuthTokensDto;
import com.example.scrabblesb.auth.dtos.TokenPayloadDto;
import com.example.scrabblesb.auth.utils.JwtService;
import com.example.scrabblesb.users.UsersService;
import com.example.scrabblesb.users.dtos.CreateUserDto;
import com.example.scrabblesb.users.dtos.RefreshDto;
import com.example.scrabblesb.users.dtos.SignInDto;
import com.example.scrabblesb.users.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A);
    private final UsersService usersService;
    private final JwtService jwtService;

    @Autowired
    public AuthService(UsersService usersService, JwtService jwtService) {
        this.usersService = usersService;
        this.jwtService = jwtService;
    }

    public void signUp(CreateUserDto createUserDto) {
        User user = usersService.findByUsername(createUserDto.getUsername());
        if (user != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }
        usersService.create(
                CreateUserDto.builder()
                        .username(createUserDto.getUsername())
                        .password(hashData(createUserDto.getPassword())).build());
    }

    public AuthTokensDto signIn(SignInDto signInDto) {
        User user = usersService.findByUsername(signInDto.getUsername());
        if (user == null || !bCryptPasswordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
        return generateNewAuthTokens(user);
    }

    public void logout(String userId) {
        usersService.updateRefreshToken(userId, null);
    }

    public AuthTokensDto refresh(RefreshDto refreshDto) {
        User user = usersService.findOneByRefreshToken(refreshDto.getRefreshToken());
        if (!jwtService.isRefreshTokenValid(refreshDto.getRefreshToken()) || user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid refresh token");
        }
        return generateNewAuthTokens(user);
    }

    private AuthTokensDto generateNewAuthTokens(User user) {
        TokenPayloadDto tokenPayloadDto = TokenPayloadDto.builder()
                .sub(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
        String accessToken = jwtService.generateToken(
                tokenPayloadDto
        );
        String refreshToken = jwtService.generateToken(
                tokenPayloadDto, true
        );
        usersService.updateRefreshToken(user.getId(), refreshToken);
        return new AuthTokensDto(accessToken, refreshToken);
    }

    private String hashData(String data) {
        return bCryptPasswordEncoder.encode(data);
    }
}
