package com.productdock.library.inventory.adapter.out.mongo;

import com.productdock.library.inventory.adapter.out.mongo.mapper.InventoryRecordMapper;
import com.productdock.library.inventory.application.port.out.persistence.InventoryRecordsPersistenceOutPort;
import com.productdock.library.inventory.domain.Inventory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Slf4j
@AllArgsConstructor
public class InventoryRecordPersistenceAdapter implements InventoryRecordsPersistenceOutPort {

    private InventoryRecordRepository inventoryRecordRepository;
    private InventoryRecordMapper inventoryRecordMapper;

    @Override
    public Optional<Inventory> findByBookId(String bookId) {
        log.debug("Find book in database by id: {}", bookId);
        var bookEntity = inventoryRecordRepository.findByBookId(bookId);
        if (bookEntity.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(inventoryRecordMapper.toDomain(bookEntity.get()));
    }

    @Override
    public void save(Inventory book) {
        log.debug("Save new book state for book: {}", book);

        var previousRecordEntity = inventoryRecordRepository.findByBookId(book.getBookId());
        var newRecordEntity = inventoryRecordMapper.toEntity(book);
        previousRecordEntity.ifPresent(inventoryRecordEntity -> newRecordEntity.setId(previousRecordEntity.get().getId()));
        inventoryRecordRepository.save(newRecordEntity);
    }
}
