package com.productdock.library.inventory.data.provider;

import com.productdock.library.inventory.book.InventoryRecordEntity;
import com.productdock.library.inventory.domain.Inventory;

public class InventoryRecordEntityMother {

    private static final String defaultBookId = "1";
    private static final int defaultBookCopies = 3;
    private static final int defaultRentedBooks = 0;
    private static final int defaultReservedBooks = 0;

    public static InventoryRecordEntity defaultInventoryRecordEntity() {
        return defaultBookEntityBuilder().build();
    }

    public static InventoryRecordEntity.InventoryRecordEntityBuilder defaultBookEntityBuilder() {
        return InventoryRecordEntity.builder()
                .bookId(defaultBookId)
                .bookCopies(defaultBookCopies)
                .rentedBooks(defaultRentedBooks)
                .reservedBooks(defaultReservedBooks);
    }
}
