package com.productdock.library.inventory.domain;

import com.productdock.library.inventory.record.RentalRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Inventory {
    private String bookId;
    private int bookCopies;
    private int reservedBooks;
    private int rentedBooks;

    public int getAvailableBooksCount(){
        return bookCopies - (rentedBooks + reservedBooks);
    }

    public void updateStateWith(RentalRecord rentalRecord) {
        setRentedBooks(rentalRecord.getRentsCount());
        setReservedBooks(rentalRecord.getReservationsCount());
    }
}
