package com.example.scrabblesb.scores;

import com.example.scrabblesb.scores.dtos.CreateScoreDto;
import com.example.scrabblesb.scores.models.Score;
import com.example.scrabblesb.users.UsersRepository;
import com.example.scrabblesb.users.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ScoresService {

    @Autowired
    private ScoresRepository scoresRepository;

    @Autowired
    private UsersRepository usersRepository;

    public void create(String userId, CreateScoreDto scoreDto) {
        // Check if the word already exists
        if (scoresRepository.findByString(scoreDto.getString()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicated word");
        }

        // Find the user by userId (assuming userId is a string)
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Create a new Score object and save it
        Score score = Score.builder()
                .string(scoreDto.getString())
                .score(scoreDto.getScore())
                .user(user)
                .build();
        scoresRepository.save(score);
    }

    public List<Score> findAll() {
        return scoresRepository.findTop10ByOrderByScoreDesc();
    }

    public void deleteAll() {
        scoresRepository.deleteAll();
    }
}

