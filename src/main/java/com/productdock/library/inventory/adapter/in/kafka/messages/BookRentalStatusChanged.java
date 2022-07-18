package com.productdock.library.inventory.adapter.in.kafka.messages;

import com.productdock.library.inventory.domain.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;

import java.io.Serializable;
import java.util.List;

@Builder
@AllArgsConstructor
public class BookRentalStatusChanged {

    public final String bookId;
    @Singular
    public final List<RentalRecord> rentalRecords;

    @Builder
    @AllArgsConstructor
    public static class RentalRecord implements Serializable {

        public final String patron;
        public final RentalStatus status;
    }
}
