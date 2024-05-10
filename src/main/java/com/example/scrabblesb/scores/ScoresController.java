package com.example.scrabblesb.scores;

import com.example.scrabblesb.auth.annotations.AuthGuard;
import com.example.scrabblesb.auth.dtos.TokenPayloadDto;
import com.example.scrabblesb.scores.constants.ScoreRules;
import com.example.scrabblesb.scores.dtos.CreateScoreDto;
import com.example.scrabblesb.scores.models.Score;
import com.example.scrabblesb.users.enums.Role;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scores")
public class ScoresController {

    @Autowired
    private ScoresService scoresService;

    @AuthGuard
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestAttribute("user") TokenPayloadDto tokenPayloadDto,
                       @Valid @RequestBody CreateScoreDto score) {
        scoresService.create(tokenPayloadDto.getSub(), score);
    }

    @AuthGuard
    @GetMapping
    public ResponseEntity<Object> findAll() {
        List<Score> scores = scoresService.findAll();
        return ResponseEntity.ok(scores);
    }

    @GetMapping("/rules")
    public ResponseEntity<Map<Character, Integer>> scoreRules() {
        return ResponseEntity.ok(ScoreRules.SCORE_RULES);
    }

    @AuthGuard({Role.ADMIN})
    @DeleteMapping
    public ResponseEntity<Object> deleteAll() {
        scoresService.deleteAll();
        return ResponseEntity.ok().build();
    }
}

