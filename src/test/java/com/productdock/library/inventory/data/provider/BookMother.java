package com.productdock.library.inventory.data.provider;

import com.productdock.library.inventory.book.BookAvailabilityMessage;
import com.productdock.library.inventory.book.InventoryRecordEntity;
import com.productdock.library.inventory.domain.Inventory;

public class BookMother {

    private static final String defaultBookId = "1";
    private static final int defaultBookCopies = 1;
    private static final int defaultRentedBooks = 0;
    private static final int defaultReservedBooks = 0;
    private static final int availableBookCount = 1;

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

    public static Inventory defaultInventory() {
        return defaultInventoryBuilder().build();
    }

    public static Inventory.InventoryBuilder defaultInventoryBuilder() {
        return Inventory.builder()
                .bookId(defaultBookId)
                .bookCopies(defaultBookCopies)
                .rentedBooks(defaultRentedBooks)
                .reservedBooks(defaultReservedBooks);
    }

    public static BookAvailabilityMessage defaultBookAvailabilityMessage() {
        return defaultBookAvailabilityMessageBuilder().build();
    }

    public static BookAvailabilityMessage.BookAvailabilityMessageBuilder defaultBookAvailabilityMessageBuilder() {
        return BookAvailabilityMessage.builder()
                .bookId(defaultBookId)
                .availableBookCount(availableBookCount);
    }
}
