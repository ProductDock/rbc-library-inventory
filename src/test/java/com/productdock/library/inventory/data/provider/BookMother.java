package com.productdock.library.inventory.data.provider;

import com.productdock.library.inventory.book.BookEntity;

public class BookMother {

    private static final String defaultBookId = "1";
    private static final int defaultBookCopies = 1;
    private static final int defaultRentedBooks = 0;
    private static final int defaultReservedBooks = 0;

    public static BookEntity defaultBook() {
        return defaultBookBuilder().build();
    }

    public static BookEntity.BookBuilder defaultBookBuilder() {
        return BookEntity.builder()
                .bookId(defaultBookId)
                .bookCopies(defaultBookCopies)
                .rentedBooks(defaultRentedBooks)
                .reservedBooks(defaultReservedBooks);
    }
}
