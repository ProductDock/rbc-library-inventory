package com.productdock.library.inventory.book;

import com.productdock.library.inventory.producer.Publisher;
import com.productdock.library.inventory.record.RentalRecord;
import org.springframework.stereotype.Service;

@Service
public record BookService(BookRepository bookRepository, Publisher publisher) {

    public void saveBook(RentalRecord rentalRecord) {
        saveNewBook(rentalRecord);
        var optionalBook = bookRepository.findById(rentalRecord.getBookId());
        if (optionalBook.isPresent()) {
            var book = optionalBook.get();
            processBookRequest(rentalRecord, book);
            bookRepository.save(book);
        }
    }

    private void saveNewBook(RentalRecord rentalRecord) {
        if (bookRepository.findById(rentalRecord.getBookId()).isEmpty()) {
            bookRepository.save(new Book(rentalRecord.getBookId(), 1, rentalRecord.getReservations().size(), rentalRecord.getRents().size()));
        }
    }

    private void processBookRequest(RentalRecord rentalRecord, Book book) {
        if (rentalRecord.getRents().size() + rentalRecord.getReservations().size() > book.getBookCopies()) {
            userTriedToTakeUnavailableBook(rentalRecord, book);
        } else {
            book.setRentedBooks(rentalRecord.getRents().size());
            book.setReservedBooks(rentalRecord.getReservations().size());
        }
    }

    private void userTriedToTakeUnavailableBook(RentalRecord rentalRecord, Book book) {
        if (rentalRecord.getRents().size() > book.getRentedBooks()) {
            rentalRecord.removeLastRent();
        } else {
            rentalRecord.removeLastReservation();
        }
        publisher.sendMessage(rentalRecord);
    }
}
