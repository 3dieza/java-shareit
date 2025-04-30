package ru.practicum.shareit.user.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseRepository<T extends CommonIdGenerator> {
    public final Map<Long, T> storage = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public T findById(final Long id) {
        return storage.get(id);
    }

    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    public T create(final T entity) {
        long id = idGenerator.incrementAndGet();
        entity.setId(id);
        storage.put(id, entity);
        return storage.get(id);
    }

    public void update(final T entity) {
        Long entityId = entity.getId();
        storage.put(entityId, entity);
    }

    public boolean delete(final Long id) {
        return storage.remove(id) != null;
    }
}