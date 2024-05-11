package com.example.scrabblesb.users;

import com.example.scrabblesb.users.dtos.CreateUserDto;
import com.example.scrabblesb.users.dtos.MeResponseDto;
import com.example.scrabblesb.users.enums.Role;
import com.example.scrabblesb.users.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void create(CreateUserDto createUserDto) {
        User createdUser = User.builder()
                .username(createUserDto.getUsername())
                .password(createUserDto.getPassword())
                .role(Role.USER)
                .build();
        usersRepository.save(createdUser);
    }

    public void updateRefreshToken(String id, String refreshToken) {
        usersRepository.findById(id)
                .map(user -> {
                    user.setRefreshToken(refreshToken);
                    return usersRepository.save(user);
                });
    }

    public MeResponseDto getMe(String username) {
        User user = usersRepository.findByUsername(username);
        return MeResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    public User findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    public User findOneByRefreshToken(String refreshToken) {
        return usersRepository.findByRefreshToken(refreshToken);
    }
}
