package ru.practicum.shareit.booking.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.booking.BookingStatus;

@Data
@Builder
public class BookingDto {
    private Long bookingId;
    @Builder.Default
    private LocalDateTime startDate = LocalDateTime.now();
    @Builder.Default
    private LocalDateTime endDate = LocalDateTime.now();
    private BookingStatus status;
}