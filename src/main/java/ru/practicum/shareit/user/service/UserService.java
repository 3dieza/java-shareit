package ru.practicum.shareit.user.service;

import java.util.List;
import java.util.Optional;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.dto.UserDto;

public interface UserService {
    UserDto addUser(User user);

    void updateUser(User user);

    boolean deleteUser(Long id);

    Optional<UserDto> getUserById(Long id);

    List<UserDto> getAllUsers();
}