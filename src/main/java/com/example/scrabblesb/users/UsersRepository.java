package com.example.scrabblesb.users;

import com.example.scrabblesb.users.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository<User, String> {
    User findByUsername(String username);

    User findByRefreshToken(String refreshToken);
}
