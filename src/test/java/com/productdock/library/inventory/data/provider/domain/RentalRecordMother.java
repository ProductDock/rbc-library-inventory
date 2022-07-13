package com.productdock.library.inventory.data.provider.domain;

import com.productdock.library.inventory.domain.RentalRecord;
import com.productdock.library.inventory.domain.RentalStatus;

import java.util.Arrays;
import java.util.List;

public class RentalRecordMother {

    private static final String defaultBookId = "1";
    private static final String defaultUserEmail = "default@gmail.com";

    private static final List<RentalRecord.RentalRecordRequest> defaultRentalRecords =
            Arrays.asList(new RentalRecord.RentalRecordRequest(defaultUserEmail, RentalStatus.RENTED),
                    new RentalRecord.RentalRecordRequest(defaultUserEmail, RentalStatus.RESERVED),
                    new RentalRecord.RentalRecordRequest(defaultUserEmail, RentalStatus.RESERVED));

    public static RentalRecord defaultRentalRecord() {
        return defaultRentalRecordBuilder().build();
    }

    public static RentalRecord.RentalRecordBuilder defaultRentalRecordBuilder() {
        return RentalRecord.builder()
                .bookId(defaultBookId)
                .rentalRecords(defaultRentalRecords);
    }
}
