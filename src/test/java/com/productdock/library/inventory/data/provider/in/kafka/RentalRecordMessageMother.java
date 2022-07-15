package com.productdock.library.inventory.data.provider.in.kafka;

import com.productdock.library.inventory.adapter.in.kafka.messages.BookRentalStatusChanged;
import com.productdock.library.inventory.domain.RentalStatus;

import java.util.Arrays;
import java.util.List;

public class RentalRecordMessageMother {

    private static final String defaultBookId = "1";
    private static final String defaultUserEmail = "default@gmail.com";

    private static final List<BookRentalStatusChanged.RentalRecordMessageRequest> defaultRentalRecords =
            Arrays.asList(new BookRentalStatusChanged.RentalRecordMessageRequest(defaultUserEmail, RentalStatus.RENTED));

    public static BookRentalStatusChanged defaultRentalRecordMessage() {
        return defaultRentalRecordMessageBuilder().build();
    }

    public static BookRentalStatusChanged.BookRentalStatusChangedBuilder defaultRentalRecordMessageBuilder() {
        return BookRentalStatusChanged.builder()
                .bookId(defaultBookId)
                .rentalRecords(defaultRentalRecords);
    }

}
