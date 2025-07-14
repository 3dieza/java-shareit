package ru.practicum.gateway.item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.gateway.item.dto.CommentDto;
import ru.practicum.gateway.item.dto.ItemDto;

import static ru.practicum.gateway.common.Constant.USER_ID_HEADER;

@Controller
@RequestMapping(path = "/items")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ItemController {

    private final ItemClient itemClient;

    /**
     * Поиск по названию/описанию
     */
    @GetMapping("/search")
    public ResponseEntity<Object> searchItemByName(@RequestHeader(USER_ID_HEADER) long userId,
                                                   @RequestParam String text) {
        log.info("Search item by text '{}', userId {}", text, userId);
        return itemClient.searchItemByName(userId, text);
    }

    /**
     * Получение всех вещей пользователя
     */
    @GetMapping
    public ResponseEntity<Object> getItemsFromUser(@RequestHeader(USER_ID_HEADER) Long ownerId) {
        log.info("Get items for user {}", ownerId);
        return itemClient.getItemsFromUser(ownerId);
    }

    /**
     * Добавление новой вещи
     */
    @PostMapping
    public ResponseEntity<Object> addItem(@RequestBody @Valid ItemDto item,
                                          @RequestHeader(USER_ID_HEADER) Long ownerId) {
        log.info("Add item {}, ownerId={}", item, ownerId);
        return itemClient.addItem(ownerId, item);
    }

    /**
     * Добавление комментария
     */
    @PostMapping("/{itemId}/comment")
    public ResponseEntity<Object> addComment(@PathVariable Long itemId,
                                             @RequestBody @Valid CommentDto commentDto,
                                             @RequestHeader(USER_ID_HEADER) Long userId) {
        log.info("Add comment to item {}, userId={}, text={}", itemId, userId, commentDto.getText());
        return itemClient.addComment(userId, itemId, commentDto);
    }

    /**
     * Обновление вещи
     */
    @PatchMapping("/{itemId}")
    public ResponseEntity<Object> updateItem(@RequestBody ItemDto item,
                                             @PathVariable Long itemId,
                                             @RequestHeader(USER_ID_HEADER) Long userId) {
        log.info("Update item {}, userId={}, fields={}", itemId, userId, item);
        return itemClient.updateItem(userId, itemId, item);
    }

    /**
     * Получение вещи по ID (с бронированиями)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getItemById(@PathVariable Long id,
                                              @RequestHeader(USER_ID_HEADER) Long userId) {
        log.info("Get item {}, userId={}", id, userId);
        return itemClient.getItemById(userId, id);
    }
}