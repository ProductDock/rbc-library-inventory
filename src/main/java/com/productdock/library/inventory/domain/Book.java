package com.productdock.library.inventory.domain;

import com.productdock.library.inventory.book.BookEntity;
import com.productdock.library.inventory.record.RentalRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Book {
    private String bookId;
    private int bookCopies;
    private int reservedBooks;
    private int rentedBooks;

    public int getAvailableBooksCount(){
        return bookCopies - (rentedBooks + reservedBooks);
    }

    public void processBookRequest(RentalRecord rentalRecord) {
        if (rentalRecord.getRentsCount() + rentalRecord.getReservationsCount() > getBookCopies()) {
            userTriedToTakeUnavailableBook(rentalRecord);
        } else {
            setRentedBooks(rentalRecord.getRentsCount());
            setReservedBooks(rentalRecord.getReservationsCount());
        }
    }

    private void userTriedToTakeUnavailableBook(RentalRecord rentalRecord) {
        if (rentalRecord.getRentsCount() > getRentedBooks()) {
            rentalRecord.removeLastRent();
        } else {
            rentalRecord.removeLastReservation();
        }
    }
}
