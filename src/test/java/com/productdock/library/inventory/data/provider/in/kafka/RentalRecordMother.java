package com.productdock.library.inventory.data.provider.in.kafka;

import com.productdock.library.inventory.adapter.in.kafka.messages.BookRentalStatusChanged;
import com.productdock.library.inventory.domain.RentalStatus;

public class RentalRecordMother {

    private static final String defaultPatron = "default@gmail.com";

    public static BookRentalStatusChanged.RentalRecord rented() {
        return rentedBuilder().build();
    }

    public static BookRentalStatusChanged.RentalRecord.RentalRecordBuilder rentedBuilder() {
        return BookRentalStatusChanged.RentalRecord.builder()
                .patron(defaultPatron)
                .status(RentalStatus.RENTED);
    }
}
