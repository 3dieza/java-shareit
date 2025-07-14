package ru.practicum.shareitgateway.request;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareitgateway.request.dto.ItemRequestDto;

import static ru.practicum.shareitgateway.common.Constant.USER_ID_HEADER;

@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class ItemRequestController {

    private final ItemRequestClient itemRequestClient;

    /**
     * Добавление запроса вещи
     */
    @PostMapping
    public ResponseEntity<Object> addItemRequest(@RequestHeader(USER_ID_HEADER) Long userId,
                                                 @Valid @RequestBody ItemRequestDto requestDto) {
        return itemRequestClient.addItemRequest(userId, requestDto);
    }

    /**
     * Получение всех своих запросов
     */
    @GetMapping
    public ResponseEntity<Object> getAllItemRequests(@RequestHeader(USER_ID_HEADER) Long userId) {
        return itemRequestClient.getAllItemRequests(userId);
    }

    /**
     * Получение одного запроса по ID
     */
    @GetMapping("/{requestId}")
    public ResponseEntity<Object> getItemRequestById(@PathVariable Long requestId,
                                                     @RequestHeader(USER_ID_HEADER) Long userId) {
        return itemRequestClient.getItemRequestById(userId, requestId);
    }
}