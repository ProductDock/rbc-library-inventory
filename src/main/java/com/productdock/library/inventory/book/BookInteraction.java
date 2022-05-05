package com.productdock.library.inventory.book;

import com.productdock.library.inventory.record.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class BookInteraction implements Serializable {
    private String patron;
    private RentalStatus status;
}

