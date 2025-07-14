package ru.practicum.shareit.item.dto;

import lombok.*;

import java.util.List;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemDtoWithBookings {

    Long id;

    @NotBlank(message = "Название не может быть пустым")
    @Size(max = 255, message = "Максимальная длина названия — 255 символов")
    String name;

    @NotBlank(message = "Описание не может быть пустым")
    @Size(max = 1000, message = "Максимальная длина описания — 1000 символов")
    String description;

    Boolean available;

    Long requestId;

    BookingShortDto lastBooking;
    BookingShortDto nextBooking;

    List<CommentDto> comments;
}