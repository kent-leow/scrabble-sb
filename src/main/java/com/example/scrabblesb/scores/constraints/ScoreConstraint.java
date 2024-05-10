package com.example.scrabblesb.scores.constraints;

import com.example.scrabblesb.scores.validators.ScoreValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ScoreValidator.class)
public @interface ScoreConstraint {
    String message() default "Invalid score";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

