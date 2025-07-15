package ru.practicum.server.booking.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.server.booking.dto.BookingCreateRequest;
import ru.practicum.server.booking.dto.BookingDto;
import ru.practicum.server.booking.dto.ItemShortDto;
import ru.practicum.server.booking.dto.UserShortDto;
import ru.practicum.server.booking.model.Booking;
import ru.practicum.server.item.model.Item;
import ru.practicum.server.user.model.User;

@Component
public class BookingMapper {

    public BookingDto toDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .start(booking.getStartDate())
                .end(booking.getEndDate())
                .status(booking.getStatus())
                .booker(UserShortDto.builder()
                        .id(booking.getBooker().getId())
                        .build())
                .item(ItemShortDto.builder()
                        .id(booking.getItem().getId())
                        .name(booking.getItem().getName())
                        .build())
                .build();
    }

    public Booking toEntity(BookingCreateRequest request, Item item, User booker) {
        return Booking.builder()
                .item(item)
                .booker(booker)
                .startDate(request.getStart())
                .endDate(request.getEnd())
                .build();
    }
}