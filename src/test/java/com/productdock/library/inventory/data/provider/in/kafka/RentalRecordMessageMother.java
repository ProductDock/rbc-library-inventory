package com.productdock.library.inventory.data.provider.in.kafka;

import com.productdock.library.inventory.adapter.in.kafka.messages.RentalRecordMessage;
import com.productdock.library.inventory.domain.RentalStatus;

import java.util.Arrays;
import java.util.List;

public class RentalRecordMessageMother {

    private static final String defaultBookId = "1";
    private static final String defaultUserEmail = "default@gmail.com";

    private static final List<RentalRecordMessage.RentalRecordMessageRequest> defaultRentalRecords =
            Arrays.asList(new RentalRecordMessage.RentalRecordMessageRequest(defaultUserEmail, RentalStatus.RENTED));

    public static RentalRecordMessage defaultRentalRecordMessage() {
        return defaultRentalRecordMessageBuilder().build();
    }

    public static RentalRecordMessage.RentalRecordMessageBuilder defaultRentalRecordMessageBuilder() {
        return RentalRecordMessage.builder()
                .bookId(defaultBookId)
                .rentalRecords(defaultRentalRecords);
    }

}
