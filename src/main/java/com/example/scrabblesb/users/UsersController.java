package com.example.scrabblesb.users;

import com.example.scrabblesb.auth.annotations.AuthGuard;
import com.example.scrabblesb.auth.dtos.TokenPayloadDto;
import com.example.scrabblesb.users.dtos.MeResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @AuthGuard
    @GetMapping("/me")
    public MeResponseDto getMe(@RequestAttribute("user") TokenPayloadDto token) {
        return usersService.getMe(token.getUsername());
    }
}
