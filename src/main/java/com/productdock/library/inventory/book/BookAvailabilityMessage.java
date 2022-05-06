package com.productdock.library.inventory.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookAvailabilityMessage implements Serializable {

    private String bookId;
    private int availableBookCount;
}
