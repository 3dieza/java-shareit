package ru.practicum.shareit.request.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;

@Component
public class ItemRequestMapper {
    public ItemRequestDto toDto(ItemRequest item) {
        return ItemRequestDto.builder()
                .id(item.getId())
                .request(item.getRequest())
                .build();
    }

    public ItemRequest toEntity(ItemRequestDto dto) {
        return ItemRequest.builder()
                .id(dto.getId())
                .request(dto.getRequest())
                .build();
    }
}