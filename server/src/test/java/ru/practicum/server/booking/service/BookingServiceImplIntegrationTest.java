
package ru.practicum.server.booking.service;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.server.booking.dto.BookingCreateRequest;
import ru.practicum.server.booking.dto.BookingDto;
import ru.practicum.server.booking.model.BookingStatus;
import ru.practicum.server.item.model.Item;
import ru.practicum.server.item.repository.ItemRepository;
import ru.practicum.server.user.model.User;
import ru.practicum.server.user.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class BookingServiceImplIntegrationTest {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void createBooking_shouldSucceed_whenValidInput() {
        User owner = userRepository.save(User.builder().name("Owner").email("owner@mail.com").build());
        User booker = userRepository.save(User.builder().name("Booker").email("booker@mail.com").build());

        Item item = itemRepository.save(Item.builder()
                .name("Item 1")
                .description("Desc")
                .available(true)
                .owner(owner)
                .build());

        BookingCreateRequest request = new BookingCreateRequest(
                item.getId(),
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2)
        );

        BookingDto bookingDto = bookingService.createBooking(request, booker.getId());

        assertThat(bookingDto).isNotNull();
        assertThat(bookingDto.getId()).isNotNull();
        assertThat(bookingDto.getStatus()).isEqualTo(BookingStatus.WAITING);
    }
}
