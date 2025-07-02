package ru.practicum.shareit.item.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final ItemService itemService;
    private static final String USER_ID_HEADER = "X-Sharer-User-Id";

    @GetMapping("/{id}")
    public ItemDto getItemById(@PathVariable Long id) {
        log.info("Запрос на получение предмета по id = {}", id);
        return itemService.findById(id);
    }

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

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@RequestBody ItemDto item,
                              @PathVariable Long itemId,
                              @RequestHeader(USER_ID_HEADER) Long userId) {
        log.info("Обновление предмета id = {} пользователем id = {}. Данные: {}", itemId, userId, item);
        return itemService.update(item, itemId, userId);
    }
}