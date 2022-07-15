package com.productdock.library.inventory.data.provider.domain;

import com.productdock.library.inventory.domain.BookRentals;
import com.productdock.library.inventory.domain.RentalStatus;

import java.util.Arrays;
import java.util.List;

public class RentalRecordMother {

    private static final String defaultBookId = "1";
    private static final String defaultUserEmail = "default@gmail.com";

    private static final List<BookRentals.RentalRecordRequest> defaultRentalRecords =
            Arrays.asList(new BookRentals.RentalRecordRequest(defaultUserEmail, RentalStatus.RENTED),
                    new BookRentals.RentalRecordRequest(defaultUserEmail, RentalStatus.RESERVED),
                    new BookRentals.RentalRecordRequest(defaultUserEmail, RentalStatus.RESERVED));

    public static BookRentals defaultRentalRecord() {
        return defaultRentalRecordBuilder().build();
    }

    public static BookRentals.RentalRecordBuilder defaultRentalRecordBuilder() {
        return BookRentals.builder()
                .bookId(defaultBookId)
                .rentalRecords(defaultRentalRecords);
    }
}
