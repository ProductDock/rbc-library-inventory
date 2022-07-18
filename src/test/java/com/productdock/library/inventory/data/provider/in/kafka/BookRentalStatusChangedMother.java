package com.productdock.library.inventory.data.provider.in.kafka;

import com.productdock.library.inventory.adapter.in.kafka.messages.BookRentalStatusChanged;

import static com.productdock.library.inventory.data.provider.in.kafka.RentalRecordMother.rented;

public class BookRentalStatusChangedMother {

    private static final String defaultBookId = "1";

    public static BookRentalStatusChanged bookRentalStatusChanged() {
        return bookRentalStatusChangedBuilder().build();
    }

    public static BookRentalStatusChanged.BookRentalStatusChangedBuilder bookRentalStatusChangedBuilder() {
        return BookRentalStatusChanged.builder()
                .bookId(defaultBookId)
                .rentalRecord(rented());
    }

}
