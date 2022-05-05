package com.productdock.library.inventory.data.provider;


import com.productdock.library.inventory.book.BookInteraction;
import com.productdock.library.inventory.record.RentalRecord;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class RentalRecordMother {

    private static final String defaultBookId = "1";
    private static final String defaultUserEmail = "default@gmail.com";
    private static final Date defaultDate = new Date();


    private static final List<BookInteraction> defaultReservations = new LinkedList<BookInteraction>(Arrays.asList(new BookInteraction(defaultUserEmail, defaultDate)));
    private static final List<BookInteraction> defaultRents = new LinkedList<BookInteraction>(Arrays.asList(new BookInteraction(defaultUserEmail, defaultDate)));

    public static RentalRecord defaultRentalRecord() {
        return defaultRentalRecordBuilder().build();
    }

    public static RentalRecord.RentalRecordBuilder defaultRentalRecordBuilder() {
        return RentalRecord.builder()
                .bookId(defaultBookId)
                .rents(defaultRents)
                .reservations(defaultReservations);
    }
}
