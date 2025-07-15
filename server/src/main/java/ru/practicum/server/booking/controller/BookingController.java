package ru.practicum.server.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.server.booking.dto.BookingCreateRequest;
import ru.practicum.server.booking.dto.BookingDto;
import ru.practicum.server.booking.service.BookingService;

import java.util.List;

import static ru.practicum.server.common.Constant.USER_ID_HEADER;

@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/{bookingId}")
    public BookingDto getBookingById(@PathVariable Long bookingId,
                                     @RequestHeader(USER_ID_HEADER) Long userId) {
        return bookingService.getBookingById(bookingId, userId);
    }

    @GetMapping
    public List<BookingDto> findAllBookingsByBooker(
            @RequestHeader(USER_ID_HEADER) Long userId,
            @RequestParam(name = "state", defaultValue = "ALL") String state
    ) {
        return bookingService.findAllBookingsByBookerId(userId, state);
    }

    @GetMapping("/owner")
    public List<BookingDto> findAllBookingsByOwner(
            @RequestHeader(USER_ID_HEADER) Long userId,
            @RequestParam(name = "state", defaultValue = "ALL") String state
    ) {
        return bookingService.findAllBookingsByOwnerId(userId, state);
    }

    @PostMapping
    public BookingDto createBooking(@RequestBody final BookingCreateRequest request,
                                    @RequestHeader(USER_ID_HEADER) Long userId) {
        return bookingService.createBooking(request, userId);
    }

    @PatchMapping("/{bookingId}")
    public BookingDto updateBookingStatus(@PathVariable Long bookingId,
                                          @RequestParam boolean approved,
                                          @RequestHeader(USER_ID_HEADER) Long userId) {
        return bookingService.updateBookingStatus(bookingId, approved, userId);
    }
}