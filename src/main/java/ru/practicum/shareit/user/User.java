package ru.practicum.shareit.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.user.repository.CommonIdGenerator;

@Data
@Builder
public class User implements CommonIdGenerator {
    private Long id;
    @NotBlank
    private String name;
    @Email
    @NotNull
    private String email;
}