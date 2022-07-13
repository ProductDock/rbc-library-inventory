package com.productdock.library.inventory.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Slf4j
public class Inventory {

    private String bookId;
    private int bookCopies;
    private int reservedBooks;
    private int rentedBooks;

    public int getAvailableBooksCount() {
        log.debug("Get available books count for book with id: {}", bookId);

        return bookCopies - (rentedBooks + reservedBooks);
    }

    public void updateStateWith(RentalRecord rentalRecord) {
        log.debug("Update book state with: {}", rentalRecord);

        setRentedBooks(rentalRecord.getRecordsCount(RentalStatus.RENTED));
        setReservedBooks(rentalRecord.getRecordsCount(RentalStatus.RESERVED));
    }
}
