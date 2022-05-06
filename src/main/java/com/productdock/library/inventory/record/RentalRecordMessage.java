package com.productdock.library.inventory.record;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RentalRecordMessage {

    private String bookId;
    private List<RentalRecordRequest> rentalRecords;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class RentalRecordRequest implements Serializable {

        private String patron;
        private RentalStatus status;
    }
}
