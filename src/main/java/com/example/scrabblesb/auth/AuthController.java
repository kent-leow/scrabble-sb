package com.example.scrabblesb.auth;

import com.example.scrabblesb.auth.annotations.AuthGuard;
import com.example.scrabblesb.auth.dtos.AuthTokensDto;
import com.example.scrabblesb.auth.dtos.TokenPayloadDto;
import com.example.scrabblesb.users.dtos.CreateUserDto;
import com.example.scrabblesb.users.dtos.RefreshDto;
import com.example.scrabblesb.users.dtos.SignInDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@Valid @RequestBody CreateUserDto createUserDto) {
        authService.signUp(createUserDto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthTokensDto signIn(@RequestBody SignInDto signInDto) {
        return authService.signIn(signInDto);
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public AuthTokensDto refresh(@RequestBody RefreshDto refreshDto) {
        return authService.refresh(refreshDto);
    }

    @AuthGuard
    @GetMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    public TokenPayloadDto getProfile(@RequestAttribute("user") TokenPayloadDto tokenPayloadDto) {
        return tokenPayloadDto;
    }

    @AuthGuard
    @GetMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(@RequestAttribute("user") TokenPayloadDto tokenPayloadDto) {
        authService.logout(tokenPayloadDto.getSub());
    }
}
