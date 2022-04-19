package com.productdock.library.inventory.book;

import com.productdock.library.inventory.producer.Publisher;
import com.productdock.library.inventory.record.RentalRecord;
import org.springframework.stereotype.Service;

@Service
public record BookService(BookRepository bookRepository, Publisher publisher) {

    public void addBook(RentalRecord rentalRecord) {
        if (bookRepository.findById(rentalRecord.getBookId()).isEmpty())
            bookRepository.save(new Book(rentalRecord.getBookId(), 3, rentalRecord.getReservations().size(), rentalRecord.getRents().size()));
    }

    public void addRentalRecord(RentalRecord rentalRecord) {
        var optionalBook = bookRepository.findById(rentalRecord.getBookId());
        if (optionalBook.isPresent()) {
            var book = optionalBook.get();
            processBookRequest(rentalRecord, book);
            bookRepository.save(book);
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
        System.out.println("ERROR: There is no book available for you to reserve or rent");
        if(rentalRecord.getRents().size() > book.getRentedBooks()) {
            rentalRecord.removeLastRent();
        } else {
            rentalRecord.removeLastReservation();
        }
        publisher.sendMessage(rentalRecord);
    }
}
