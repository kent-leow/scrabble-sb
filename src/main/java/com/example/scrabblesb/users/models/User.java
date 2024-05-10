package com.example.scrabblesb.users.models;

import com.example.scrabblesb.users.enums.Role;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "users")
@Data
@Builder
public class User {
    @Id
    private String id;

    @Field(name = "username")
    private String username;

    @Field(name = "role")
    private Role role;

    @Field(name = "password")
    private String password;

    @Field(name = "refreshToken")
    private String refreshToken;
}
