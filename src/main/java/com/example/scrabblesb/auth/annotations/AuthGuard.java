package com.example.scrabblesb.auth.annotations;

import com.example.scrabblesb.users.enums.Role;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@SecurityRequirement(name = "bearerAuth")
public @interface AuthGuard {
    Role[] value() default {}; // Role names
}

