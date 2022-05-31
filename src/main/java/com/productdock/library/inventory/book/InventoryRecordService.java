package com.productdock.library.inventory.book;

import com.productdock.library.inventory.domain.Inventory;
import com.productdock.library.inventory.domain.RentalRecord;
import com.productdock.library.inventory.exception.InventoryException;
import com.productdock.library.inventory.producer.Publisher;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public record InventoryRecordService(InventoryRecordRepository inventoryRecordRepository, Publisher publisher,
                                     InventoryRecordMapper inventoryRecordMapper) {

    @SneakyThrows
    public void updateBookState(RentalRecord rentalRecord) {
        log.debug("Update book state for book {} with rental record: rents count - {}, reservations count - {}", rentalRecord.getBookId(), rentalRecord.getRentsCount(), rentalRecord.getReservationsCount());

        var book = getInventoryFrom(rentalRecord.getBookId());
        book.updateStateWith(rentalRecord);
        saveInventoryRecord(book);
        publisher.sendMessage(new BookAvailabilityMessage(book.getBookId(), book.getAvailableBooksCount()));
    }

    private Inventory getInventoryFrom(String bookId) {
        log.debug("Find book in database by id: {}", bookId);

        var optionalBook = inventoryRecordRepository.findByBookId(bookId);
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
        log.debug("Save new book state for book: {}", book);

        var previousRecordEntity = inventoryRecordRepository.findByBookId(book.getBookId());
        var newRecordEntity = inventoryRecordMapper.toEntity(book);
        if (previousRecordEntity.isPresent()) {
            newRecordEntity.setId(previousRecordEntity.get().getId());
        }
        inventoryRecordRepository.save(newRecordEntity);
    }
}
