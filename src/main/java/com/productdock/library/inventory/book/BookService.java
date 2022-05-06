package com.productdock.library.inventory.book;

import com.productdock.library.inventory.domain.Book;
import com.productdock.library.inventory.producer.Publisher;
import com.productdock.library.inventory.record.RentalRecord;
import org.springframework.stereotype.Service;


@Service
public record BookService(BookRepository bookRepository, Publisher publisher, BookMapper bookMapper) {

    public void updateBookState(RentalRecord rentalRecord) throws Exception{
        saveNewBook(rentalRecord);
        var optionalBook = bookRepository.findById(rentalRecord.getBookId());
        if (optionalBook.isPresent()) {
            var bookEntity = optionalBook.get();
            Book book = bookMapper.toDomain(bookEntity);
            book.processRentalRecords(rentalRecord);
            bookRepository.save(bookMapper.toEntity(book));
            publisher.sendMessage(BookAvailabilityMessage.builder().bookId(book.getBookId()).availableBookCount(book.getAvailableBooksCount()).build());
        }
    }

    private void saveNewBook(RentalRecord rentalRecord) {
        if (bookRepository.findById(rentalRecord.getBookId()).isEmpty()) {
            bookRepository.save(BookEntity.builder().bookId(rentalRecord.getBookId()).bookCopies(1).reservedBooks(rentalRecord.getReservationsCount()).rentedBooks(rentalRecord.getRentsCount()).build());
        }
    }
}
