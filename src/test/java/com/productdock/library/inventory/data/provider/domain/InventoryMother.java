package com.productdock.library.inventory.data.provider.domain;

import com.productdock.library.inventory.domain.Inventory;

public class InventoryMother {

    private static final String defaultBookId = "1";
    private static final int defaultBookCopies = 3;
    private static final int defaultRentedBooks = 0;
    private static final int defaultReservedBooks = 0;

    public static Inventory inventory() {
        return defaultInventoryBuilder().build();
    }

    public static Inventory.InventoryBuilder defaultInventoryBuilder() {
        return Inventory.builder()
                .bookId(defaultBookId)
                .bookCopies(defaultBookCopies)
                .rentedBooks(defaultRentedBooks)
                .reservedBooks(defaultReservedBooks);
    }
}
