package com.productdock.library.inventory.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Slf4j
public class RentalRecord implements Serializable {

    private String bookId;
    private List<RentalRecordRequest> rentalRecords;

    public int getRecordsCount(RentalStatus status) {
        return (int) rentalRecords.stream().filter(r -> r.getStatus().equals(status)).count();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class RentalRecordRequest implements Serializable {

        private String patron;
        private RentalStatus status;
    }
}
