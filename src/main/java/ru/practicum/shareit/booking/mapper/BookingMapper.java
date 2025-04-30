package ru.practicum.shareit.booking.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.booking.dto.BookingDto;

@Component
public class BookingMapper {
    public BookingDto toDto(Booking booking) {
        return BookingDto.builder()
                .bookingId(booking.getBookingId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .status(booking.getStatus())
                .build();
    }

    public Booking toEntity(BookingDto bookingDto) {
        return Booking.builder()
                .bookingId(bookingDto.getBookingId())
                .startDate(bookingDto.getStartDate())
                .endDate(bookingDto.getEndDate())
                .status(bookingDto.getStatus())
                .build();
    }
}