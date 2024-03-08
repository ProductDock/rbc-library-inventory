package com.productdock.library.inventory.application.service;

import com.productdock.library.inventory.application.port.in.DeleteBookUseCase;
import com.productdock.library.inventory.application.port.out.persistence.InventoryRecordsPersistenceOutPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class DeleteBookService implements DeleteBookUseCase {

    private InventoryRecordsPersistenceOutPort inventoryRecordRepository;

    @Override
    public void deleteBook(String bookId) {
        var bookInventory = inventoryRecordRepository.findByBookId(bookId);
        log.warn("Found by book id {}: {}", bookId, bookInventory);
        if(bookInventory.isPresent()){
            inventoryRecordRepository.deleteByBookId(bookId);
        }
    }
}
