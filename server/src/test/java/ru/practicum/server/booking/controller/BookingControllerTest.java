package ru.practicum.server.booking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.server.booking.dto.BookingCreateRequest;
import ru.practicum.server.booking.dto.BookingDto;
import ru.practicum.server.booking.dto.ItemShortDto;
import ru.practicum.server.booking.dto.UserShortDto;
import ru.practicum.server.booking.model.BookingStatus;
import ru.practicum.server.booking.service.BookingService;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.practicum.server.common.Constant.USER_ID_HEADER;

@WebMvcTest(BookingController.class)
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @Autowired
    private ObjectMapper objectMapper;

    private BookingDto getSampleDto() {
        return BookingDto.builder()
                .id(1L)
                .start(LocalDateTime.now().plusHours(1))
                .end(LocalDateTime.now().plusHours(2))
                .status(BookingStatus.WAITING)
                .booker(UserShortDto.builder().id(2L).build())
                .item(ItemShortDto.builder().id(3L).name("Item").build())
                .build();
    }

    @Test
    void getBookingById_shouldReturnOk() throws Exception {
        Mockito.when(bookingService.getBookingById(1L, 10L)).thenReturn(getSampleDto());

        mockMvc.perform(get("/bookings/1")
                        .header(USER_ID_HEADER, 10))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void findAllBookingsByBooker_shouldReturnOk() throws Exception {
        Mockito.when(bookingService.findAllBookingsByBookerId(10L, "ALL"))
                .thenReturn(List.of(getSampleDto()));

        mockMvc.perform(get("/bookings")
                        .header(USER_ID_HEADER, 10))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)));
    }

    @Test
    void findAllBookingsByOwner_shouldReturnOk() throws Exception {
        Mockito.when(bookingService.findAllBookingsByOwnerId(10L, "ALL"))
                .thenReturn(List.of(getSampleDto()));

        mockMvc.perform(get("/bookings/owner")
                        .header(USER_ID_HEADER, 10))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)));
    }

    @Test
    void createBooking_shouldReturnOk() throws Exception {
        BookingCreateRequest request = new BookingCreateRequest(
                3L,
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2)
        );

        Mockito.when(bookingService.createBooking(any(), anyLong()))
                .thenReturn(getSampleDto());

        mockMvc.perform(post("/bookings")
                        .header(USER_ID_HEADER, 10)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void updateBookingStatus_shouldReturnOk() throws Exception {
        Mockito.when(bookingService.updateBookingStatus(1L, true, 10L))
                .thenReturn(getSampleDto());

        mockMvc.perform(patch("/bookings/1?approved=true")
                        .header(USER_ID_HEADER, 10))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }
}