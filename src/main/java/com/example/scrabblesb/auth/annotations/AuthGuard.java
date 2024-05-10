package com.example.scrabblesb.auth.annotations;

import com.example.scrabblesb.users.enums.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuthGuard {
    Role[] value() default {}; // Role names
}

