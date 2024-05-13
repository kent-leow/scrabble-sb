package com.example.scrabblesb.scores.validators;

import com.example.scrabblesb.scores.constants.ScoreRules;
import com.example.scrabblesb.scores.constraints.ScoreConstraint;
import com.example.scrabblesb.scores.dtos.CreateScoreDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ScoreValidator implements ConstraintValidator<ScoreConstraint, CreateScoreDto> {
    @Override
    public void initialize(ScoreConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(CreateScoreDto createScoreDto, ConstraintValidatorContext context) {
        // Calculate the score based on the scoringRules
        int score = createScoreDto.getString().chars()
                .mapToObj(c -> (char) c)
                .mapToInt(c -> ScoreRules.SCORE_RULES.getOrDefault(Character.toUpperCase(c), 0))
                .sum();

        // Check if the calculated score matches the provided value
        if (score != createScoreDto.getScore()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "No cheating!");
        }
        return true;
    }
}

