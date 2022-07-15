package com.productdock.library.inventory.domain;

import com.productdock.library.inventory.data.provider.domain.BookRentalsMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookRentalsShould {

    @Test
    void getRecordsCount() {
        var bookRentals = BookRentalsMother.bookRentals();

        int rentedBooksCount = bookRentals.getRecordsCount(RentalStatus.RENTED);
        int reservedBooksCount = bookRentals.getRecordsCount(RentalStatus.RESERVED);

        assertThat(rentedBooksCount).isEqualTo(1);
        assertThat(reservedBooksCount).isEqualTo(2);
    }
}
