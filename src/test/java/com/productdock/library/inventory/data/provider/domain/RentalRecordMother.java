package com.productdock.library.inventory.data.provider.domain;

import com.productdock.library.inventory.domain.RentalRecord;

public class RentalRecordMother {

    private static final String defaultBookId = "1";
    private static final int defaultRentsCount = 1;
    private static final int defaultReservationsCount = 2;

    public static RentalRecord defaultRentalRecord() {
        return defaultRentalRecordBuilder().build();
    }

    public static RentalRecord.RentalRecordBuilder defaultRentalRecordBuilder() {
        return RentalRecord.builder()
                .bookId(defaultBookId)
                .rentsCount(defaultRentsCount)
                .reservationsCount(defaultReservationsCount);
    }
}
