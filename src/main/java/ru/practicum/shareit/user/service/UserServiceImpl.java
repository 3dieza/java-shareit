package ru.practicum.shareit.user.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public UserDto addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User can't be null");
        }
        boolean emailExist = userRepository.isEmailExist(user.getEmail());
        if (emailExist) {
            throw new IllegalArgumentException("Email already exist");
        }

        User userCreated = userRepository.create(user);
        return userMapper.toUserDto(userCreated);
    }

    @Override
    public void updateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User can't be null");
        }
        getUserById(user.getId());
        userRepository.update(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        getUserById(id);
        return userRepository.delete(id);
    }

    @Override
    public Optional<UserDto> getUserById(Long id) {
        return Optional.ofNullable(userMapper.toUserDto(userRepository.findById(id)));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userMapper.toListUserDto(userRepository.findAll());
    }
}