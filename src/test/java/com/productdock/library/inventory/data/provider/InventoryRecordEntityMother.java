package com.productdock.library.inventory.data.provider;

import com.productdock.library.inventory.adapter.out.mongo.entity.InventoryRecordEntity;

public class InventoryRecordEntityMother {

    private static final String defaultId = "1111111";
    private static final String defaultBookId = "1";
    private static final int defaultBookCopies = 3;
    private static final int defaultRentedBooks = 0;
    private static final int defaultReservedBooks = 0;

    public static InventoryRecordEntity defaultInventoryRecordEntity() {
        return defaultBookEntityBuilder().build();
    }

    public static InventoryRecordEntity.InventoryRecordEntityBuilder defaultBookEntityBuilder() {
        return InventoryRecordEntity.builder()
                .id(defaultId)
                .bookId(defaultBookId)
                .bookCopies(defaultBookCopies)
                .rentedBooks(defaultRentedBooks)
                .reservedBooks(defaultReservedBooks);
    }
}
