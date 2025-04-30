package ru.practicum.shareit.user.repository;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.User;

@Repository
public class UserRepository extends BaseRepository<User> {

    public boolean isEmailExist(String email) {
        return findAll().stream()
                .anyMatch(user -> email.equals(user.getEmail()));
    }
}