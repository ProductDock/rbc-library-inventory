package com.productdock.library.inventory.data.provider;

import com.productdock.library.inventory.record.RentalRecordMessage;
import com.productdock.library.inventory.record.RentalStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RentalRecordMessageMother {

    private static final String defaultBookId = "1";
    private static final String defaultUserEmail = "default@gmail.com";

    private static final List<RentalRecordMessage.RentalRecordRequest> defaultInteractionsMessage =
            Arrays.asList(new RentalRecordMessage.RentalRecordRequest(defaultUserEmail, RentalStatus.RENTED));

    public static RentalRecordMessage defaultRentalRecordMessage() {
        return defaultRentalRecordMessageBuilder().build();
    }

    public static RentalRecordMessage.RentalRecordMessageBuilder defaultRentalRecordMessageBuilder() {
        return RentalRecordMessage.builder()
                .bookId(defaultBookId)
                .rentalRecords(defaultInteractionsMessage);
    }

}
