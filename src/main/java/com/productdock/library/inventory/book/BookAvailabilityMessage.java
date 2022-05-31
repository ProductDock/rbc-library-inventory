package com.productdock.library.inventory.book;

import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BookAvailabilityMessage implements Serializable {

    private String bookId;
    private int availableBookCount;
}
