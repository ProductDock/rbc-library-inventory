package com.productdock.library.inventory.book;

import com.productdock.library.inventory.domain.Inventory;
import com.productdock.library.inventory.exception.InventoryException;
import com.productdock.library.inventory.producer.Publisher;
import com.productdock.library.inventory.domain.RentalRecord;
import org.springframework.stereotype.Service;


@Service
public record InventoryRecordService(InventoryRecordRepository inventoryRecordRepository, Publisher publisher, InventoryRecordMapper inventoryRecordMapper) {

    public void updateBookState(RentalRecord rentalRecord) throws Exception {
        var optionalBook = inventoryRecordRepository.findById(rentalRecord.getBookId());
        if (optionalBook.isEmpty()) {
            throw new InventoryException("Book does not exist in inventory!");
        }
        var bookEntity = optionalBook.get();
        Inventory book = inventoryRecordMapper.toDomain(bookEntity);
        book.updateStateWith(rentalRecord);
        inventoryRecordRepository.save(inventoryRecordMapper.toEntity(book));
        publisher.sendMessage(new BookAvailabilityMessage(book.getBookId(), book.getAvailableBooksCount()));
    }
}
