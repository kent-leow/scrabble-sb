package com.example.scrabblesb.scores.models;

import com.example.scrabblesb.users.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "scores")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    @Id
    private String id;

    private String string;

    private int score;

    @DBRef
    private User user;
}
