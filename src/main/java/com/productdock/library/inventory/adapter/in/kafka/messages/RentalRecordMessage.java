package com.productdock.library.inventory.adapter.in.kafka.messages;

import com.productdock.library.inventory.domain.RentalStatus;
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
    private List<RentalRecordMessageRequest> rentalRecords;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class RentalRecordMessageRequest implements Serializable {

        private String patron;
        private RentalStatus status;
    }
}
