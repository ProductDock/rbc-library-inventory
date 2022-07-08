package com.productdock.library.inventory.adapter.out.mongo;

import com.productdock.library.inventory.adapter.out.mongo.mapper.InventoryRecordMapper;
import com.productdock.library.inventory.application.port.out.persistence.InventoryRecordsPersistenceOutPort;
import com.productdock.library.inventory.domain.Inventory;
import com.productdock.library.inventory.domain.exception.InventoryException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@AllArgsConstructor
public class InventoryRecordsRepository implements InventoryRecordsPersistenceOutPort {

    private InventoryRecordEntityRepository inventoryRecordEntityRepository;
    private InventoryRecordMapper inventoryRecordMapper;

    @Override
    public Inventory getInventoryFrom(String bookId) {
        log.debug("Find book in database by id: {}", bookId);
        var bookEntity = inventoryRecordEntityRepository.findByBookId(bookId).orElseThrow(() -> new InventoryException("Book does not exist in inventory!"));
        return inventoryRecordMapper.toDomain(bookEntity);
    }

    @Override
    public void saveInventoryRecord(Inventory book) {
        log.debug("Save new book state for book: {}", book);

        var previousRecordEntity = inventoryRecordEntityRepository.findByBookId(book.getBookId());
        var newRecordEntity = inventoryRecordMapper.toEntity(book);
        previousRecordEntity.ifPresent(inventoryRecordEntity -> newRecordEntity.setId(previousRecordEntity.get().getId()));
        inventoryRecordEntityRepository.save(newRecordEntity);
    }
}
