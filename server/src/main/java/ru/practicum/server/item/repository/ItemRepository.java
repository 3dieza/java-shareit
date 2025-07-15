package ru.practicum.server.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.server.item.model.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    boolean existsByOwnerId(Long ownerId);

    List<Item> findAllByRequestId(Long requestId);
}
