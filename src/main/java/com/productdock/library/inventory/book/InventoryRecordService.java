package com.productdock.library.inventory.book;

import com.productdock.library.inventory.domain.Inventory;
import com.productdock.library.inventory.domain.RentalRecord;
import com.productdock.library.inventory.exception.InventoryException;
import com.productdock.library.inventory.producer.Publisher;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;


@Service
public record InventoryRecordService(InventoryRecordRepository inventoryRecordRepository, Publisher publisher,
                                     InventoryRecordMapper inventoryRecordMapper) {

    @SneakyThrows
    public void updateBookState(RentalRecord rentalRecord) {
        System.out.println(rentalRecord);
        System.out.println(inventoryRecordRepository.findByBookId(rentalRecord.getBookId()));
        var book = getInventoryFrom(rentalRecord.getBookId());
        book.updateStateWith(rentalRecord);
        saveInventoryRecord(book);
        publisher.sendMessage(new BookAvailabilityMessage(book.getBookId(), book.getAvailableBooksCount()));
    }

    private Inventory getInventoryFrom(String bookId) {
        var optionalBook = inventoryRecordRepository.findByBookId(bookId);
        System.out.println(inventoryRecordRepository.findByBookId(bookId));
        if (optionalBook.isEmpty()) {
            throw new InventoryException("Book does not exist in inventory!");
        }
        var bookEntity = optionalBook.get();
        return inventoryRecordMapper.toDomain(bookEntity);
    }

    public int getAvailableBooksCount(String bookId) {
        var book = getInventoryFrom(bookId);
        return book.getAvailableBooksCount();
    }

    private void saveInventoryRecord(Inventory book) {
        var previousRecordEntity = inventoryRecordRepository.findByBookId(book.getBookId());
        var newRecordEntity = inventoryRecordMapper.toEntity(book);
        if (previousRecordEntity.isPresent()) {
            newRecordEntity.setId(previousRecordEntity.get().getId());
        }
        inventoryRecordRepository.save(newRecordEntity);
    }
}
