package ru.practicum.shareit.booking;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Booking {
    private Long bookingId;
    @Builder.Default
    private LocalDateTime startDate = LocalDateTime.now();
    @Builder.Default
    private LocalDateTime endDate = LocalDateTime.now();
    private BookingStatus status;
    private String review;
}

