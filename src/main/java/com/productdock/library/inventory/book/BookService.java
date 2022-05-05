package com.productdock.library.inventory.book;

import com.productdock.library.inventory.domain.Book;
import com.productdock.library.inventory.producer.Publisher;
import com.productdock.library.inventory.record.RentalRecord;
import org.springframework.stereotype.Service;

@Service
public record BookService(BookRepository bookRepository, Publisher publisher, BookMapper bookMapper) {

    public void saveBook(RentalRecord rentalRecord) {
        saveNewBook(rentalRecord);
        var optionalBook = bookRepository.findById(rentalRecord.getBookId());
        if (optionalBook.isPresent()) {
            var bookEntity = optionalBook.get();
            Book book = bookMapper.toDomain(bookEntity);
            book.processBookRequest(rentalRecord);
            bookRepository.save(bookMapper.toEntity(book));
            publisher.sendMessage(new BookAvailabilityMessage(book.getBookId(), book.getAvailableBooksCount()));
        }
    }

//    private BookAvailabilityMessage calculateBookAvailability(BookEntity book) {
//        int availableBookCount = book.getBookCopies() - (book.getRentedBooks() + book.getReservedBooks());
//        return new BookAvailabilityMessage(book.getBookId(), availableBookCount);
//    }

    private void saveNewBook(RentalRecord rentalRecord) {
        if (bookRepository.findById(rentalRecord.getBookId()).isEmpty()) {
            bookRepository.save(new BookEntity(rentalRecord.getBookId(), 1, rentalRecord.getReservationsCount(), rentalRecord.getRentsCount()));
        }
    }

//    private void processBookRequest(RentalRecord rentalRecord, BookEntity book) {
//        if (rentalRecord.getRents().size() + rentalRecord.getReservationsCount().size() > book.getBookCopies()) {
//            userTriedToTakeUnavailableBook(rentalRecord, book);
//        } else {
//            book.setRentedBooks(rentalRecord.getRentsCount());
//            book.setReservedBooks(rentalRecord.getReservationsCount());
//        }
//    }
//
//    private void userTriedToTakeUnavailableBook(RentalRecord rentalRecord, BookEntity book) {
//        if (rentalRecord.getRents().size() > book.getRentedBooks()) {
//            rentalRecord.removeLastRent();
//        } else {
//            rentalRecord.removeLastReservation();
//        }
//        publisher.sendMessage(rentalRecord);
//    }
}
