package com.productdock.library.inventory.domain;

import com.productdock.library.inventory.adapter.in.kafka.messages.RentalRecordMessage;
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
    private int rentsCount;
    private int reservationsCount;

    public RentalRecord(String bookId, List<RentalRecordMessage.RentalRecordRequest> rentalRecordRequests) {
        this.bookId = bookId;
        setRentsCountFrom(rentalRecordRequests);
        setReservationsCountFrom(rentalRecordRequests);
    }

    private void setRentsCountFrom(List<RentalRecordMessage.RentalRecordRequest> rentalRecordRequests) {
        rentsCount = getCountByStatus(rentalRecordRequests, RentalStatus.RENTED);
    }

    private void setReservationsCountFrom(List<RentalRecordMessage.RentalRecordRequest> rentalRecordRequests) {
        reservationsCount = getCountByStatus(rentalRecordRequests, RentalStatus.RESERVED);
    }

    private int getCountByStatus(List<RentalRecordMessage.RentalRecordRequest> rentalRecordRequests, RentalStatus status) {
        log.debug("Get rental records count by status: {}", status);

        return (int) rentalRecordRequests.stream().filter(book -> book.getStatus().equals(status)).count();
    }
}
