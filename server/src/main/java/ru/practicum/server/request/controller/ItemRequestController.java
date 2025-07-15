package ru.practicum.server.request.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.server.request.dto.ItemRequestDto;
import ru.practicum.server.request.service.ItemRequestService;

import java.util.List;

import static ru.practicum.server.common.Constant.USER_ID_HEADER;

@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class ItemRequestController {
    private final ItemRequestService requestService;

    @PostMapping
    public ItemRequestDto addItemRequest(@RequestHeader(USER_ID_HEADER) Long userId,
                                         @RequestBody ItemRequestDto requestDto) {
        return requestService.addItemRequest(userId, requestDto);
    }

    @GetMapping
    public List<ItemRequestDto> getAllItemRequests(@RequestHeader(USER_ID_HEADER) Long userId) {
        return requestService.getAllItemRequests(userId);
    }

    @GetMapping("/{requestId}")
    public ItemRequestDto getAllItemRequestById(@PathVariable Long requestId,
                                                @RequestHeader(USER_ID_HEADER) Long userId) {
        return requestService.getItemRequest(requestId, userId);
    }

}
