package com.productdock.library.inventory.domain;

import com.productdock.library.inventory.data.provider.domain.RentalRecordMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookRentalsShould {

    @Test
    void getRecordsCount() {
        var rentalRecord = RentalRecordMother.defaultRentalRecord();

        int rentedBooksCount = rentalRecord.getRecordsCount(RentalStatus.RENTED);
        int reservedBooksCount = rentalRecord.getRecordsCount(RentalStatus.RESERVED);

        assertThat(rentedBooksCount).isEqualTo(1);
        assertThat(reservedBooksCount).isEqualTo(2);
    }
}
