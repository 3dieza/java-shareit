package ru.practicum.shareit.item.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoWithBookings;
import ru.practicum.shareit.item.service.ItemService;

import java.util.List;

import static ru.practicum.shareit.common.Constants.USER_ID_HEADER;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/search")
    public List<ItemDto> searchItemByName(@RequestParam String text) {
        log.info("Поиск предметов по тексту запроса: '{}'", text);
        return itemService.searchItemByText(text);
    }

    @GetMapping
    public List<ItemDto> getItemsFromUser(@RequestHeader(USER_ID_HEADER) Long ownerId) {
        log.info("Получение всех предметов пользователя с id = {}", ownerId);
        return itemService.findAllItemsFromUser(ownerId);
    }

    @PostMapping
    public ItemDto addItem(@RequestBody @Valid ItemDto item,
                           @RequestHeader(USER_ID_HEADER) Long ownerId) {
        log.info("Создание предмета пользователем id = {}. Данные: {}", ownerId, item);
        return itemService.crete(item, ownerId);
    }

    @PostMapping("/{itemId}/comment")
    public CommentDto addComment(@PathVariable Long itemId,
                                 @RequestBody CommentDto commentDto,
                                 @RequestHeader(USER_ID_HEADER) Long userId) {
        log.info("Добавление комментария к предмету id = {} от пользователя id = {}. Текст: {}",
                itemId, userId, commentDto.getText());
        return itemService.addComment(itemId, userId, commentDto);
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@RequestBody ItemDto item,
                              @PathVariable Long itemId,
                              @RequestHeader(USER_ID_HEADER) Long userId) {
        log.info("Обновление предмета id = {} пользователем id = {}. Данные: {}", itemId, userId, item);
        return itemService.update(item, itemId, userId);
    }

    @GetMapping("/{id}")
    public ItemDtoWithBookings getItemById(@PathVariable Long id,
                                           @RequestHeader(USER_ID_HEADER) Long userId) {
        log.info("Получение предмета id = {} пользователем id = {}", id, userId);
        return itemService.getItemById(id, userId);
    }
}