package ru.practicum.shareit.item.model;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
    private Long id;
    private String name;
    private String description;
    private Long ownerId;
    private boolean isAvailable;
    @Nullable
    private Long requestId;
    @Nullable
    private String review;
}