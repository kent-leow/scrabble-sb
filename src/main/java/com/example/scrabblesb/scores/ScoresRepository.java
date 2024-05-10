package com.example.scrabblesb.scores;

import com.example.scrabblesb.scores.models.Score;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoresRepository extends MongoRepository<Score, String> {
    Score findByString(String string);

    List<Score> findTop10ByOrderByScoreDesc();
}
