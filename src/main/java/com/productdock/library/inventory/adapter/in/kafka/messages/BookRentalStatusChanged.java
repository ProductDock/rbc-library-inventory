package com.productdock.library.inventory.adapter.in.kafka.messages;

import com.productdock.library.inventory.domain.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
@AllArgsConstructor
public class BookRentalStatusChanged {

    public final String bookId;
    public final List<RentalRecordMessageRequest> rentalRecords;

    @Builder
    @AllArgsConstructor
    public static class RentalRecordMessageRequest implements Serializable {

        public final String patron;
        public final RentalStatus status;
    }
}
