package ru.practicum.shareit.booking.model;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.common.CommonIdGenerator;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class Booking implements CommonIdGenerator {
    Long id;
    Item item;
    User booker;

    @FutureOrPresent
    LocalDateTime startDate;

    @Future
    LocalDateTime endDate;

    BookingStatus status;
}