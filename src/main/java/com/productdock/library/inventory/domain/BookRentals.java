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
public class BookRentals implements Serializable {

    private String bookId;
    private List<BookCopyRentalState> bookCopiesRentalState;

    public int getRecordsCount(RentalStatus status) {
        return (int) bookCopiesRentalState.stream().filter(r -> r.getStatus().equals(status)).count();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class BookCopyRentalState implements Serializable {

        private String patron;
        private RentalStatus status;
    }
}
