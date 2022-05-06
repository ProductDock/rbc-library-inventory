package com.productdock.library.inventory.book;

import com.productdock.library.inventory.domain.Inventory;
import com.productdock.library.inventory.producer.Publisher;
import com.productdock.library.inventory.record.RentalRecord;
import org.springframework.stereotype.Service;


@Service
public record InventoryRecordService(InventoryRecordRepository inventoryRecordRepository, Publisher publisher, InventoryRecordMapper inventoryRecordMapper) {

    public void updateBookState(RentalRecord rentalRecord) throws Exception{
        saveNewBook(rentalRecord);
        var optionalBook = inventoryRecordRepository.findById(rentalRecord.getBookId());
        if (optionalBook.isPresent()) {
            var bookEntity = optionalBook.get();
            Inventory book = inventoryRecordMapper.toDomain(bookEntity);
            book.updateStateWith(rentalRecord);
            inventoryRecordRepository.save(inventoryRecordMapper.toEntity(book));
            publisher.sendMessage(BookAvailabilityMessage.builder().bookId(book.getBookId()).availableBookCount(book.getAvailableBooksCount()).build());
        }
    }

    private void saveNewBook(RentalRecord rentalRecord) {
        if (inventoryRecordRepository.findById(rentalRecord.getBookId()).isEmpty()) {
            inventoryRecordRepository.save(InventoryRecordEntity.builder().bookId(rentalRecord.getBookId()).bookCopies(1).reservedBooks(rentalRecord.getReservationsCount()).rentedBooks(rentalRecord.getRentsCount()).build());
        }
    }
}
