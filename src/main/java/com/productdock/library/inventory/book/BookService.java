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
            publisher.sendMessage(new BookAvailabilityMessage(book.getBookId(), book.getAvailableBooksCount()));
        }
    }

    private void saveNewBook(RentalRecord rentalRecord) {
        if (bookRepository.findById(rentalRecord.getBookId()).isEmpty()) {
            bookRepository.save(new BookEntity(rentalRecord.getBookId(), 1, rentalRecord.getReservationsCount(), rentalRecord.getRentsCount()));
        }
    }
}
