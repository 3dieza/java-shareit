package ru.practicum.server.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.server.item.dto.CommentDto;
import ru.practicum.server.item.dto.ItemDto;
import ru.practicum.server.item.dto.ItemDtoWithBookings;
import ru.practicum.server.item.service.ItemService;

import java.util.List;

import static ru.practicum.server.common.Constant.USER_ID_HEADER;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/search")
    public List<ItemDto> searchItemByName(@RequestParam String text) {
        return itemService.searchItemByText(text);
    }

    @GetMapping
    public List<ItemDto> getItemsFromUser(@RequestHeader(USER_ID_HEADER) Long ownerId) {
        return itemService.findAllItemsFromUser(ownerId);
    }

    @PostMapping
    public ItemDto addItem(@RequestBody ItemDto item,
                           @RequestHeader(USER_ID_HEADER) Long ownerId) {
        return itemService.crete(item, ownerId);
    }

    @PostMapping("/{itemId}/comment")
    public CommentDto addComment(@PathVariable Long itemId,
                                 @RequestBody CommentDto commentDto,
                                 @RequestHeader(USER_ID_HEADER) Long userId) {
        return itemService.addComment(itemId, userId, commentDto);
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@RequestBody ItemDto item,
                              @PathVariable Long itemId,
                              @RequestHeader(USER_ID_HEADER) Long userId) {
        return itemService.update(item, itemId, userId);
    }

    @GetMapping("/{id}")
    public ItemDtoWithBookings getItemById(@PathVariable Long id,
                                           @RequestHeader(USER_ID_HEADER) Long userId) {
        return itemService.getItemById(id, userId);
    }
}