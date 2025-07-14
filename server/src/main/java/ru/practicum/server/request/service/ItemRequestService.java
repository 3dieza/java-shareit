package ru.practicum.server.request.service;

import ru.practicum.server.request.dto.ItemRequestDto;

import java.util.List;

public interface ItemRequestService {

    ItemRequestDto addItemRequest(Long userId, ItemRequestDto requestDto);

    List<ItemRequestDto> getAllItemRequests(Long userId);

    ItemRequestDto getItemRequest(Long requestId, Long userId);
}
