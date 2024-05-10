package com.example.scrabblesb.scores.dtos;

import com.example.scrabblesb.scores.constraints.ScoreConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@ScoreConstraint
public class CreateScoreDto {
    @NotEmpty(message = "Empty string is not allowed.")
    private String string;

    @NotNull
    private Integer score;
}
