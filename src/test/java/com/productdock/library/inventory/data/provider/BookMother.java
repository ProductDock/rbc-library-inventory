package com.productdock.library.inventory.data.provider;

import com.productdock.library.inventory.book.BookAvailabilityMessage;
import com.productdock.library.inventory.book.BookEntity;
import com.productdock.library.inventory.domain.Book;
import com.productdock.library.inventory.record.RentalRecordMessage;
import com.productdock.library.inventory.record.RentalStatus;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BookMother {

    private static final String defaultBookId = "1";
    private static final int defaultBookCopies = 1;
    private static final int defaultRentedBooks = 0;
    private static final int defaultReservedBooks = 0;
    private static final int availableBookCount = 1;

    public static BookEntity defaultBookEntity() {
        return defaultBookEntityBuilder().build();
    }

    public static BookEntity.BookEntityBuilder defaultBookEntityBuilder() {
        return BookEntity.builder()
                .bookId(defaultBookId)
                .bookCopies(defaultBookCopies)
                .rentedBooks(defaultRentedBooks)
                .reservedBooks(defaultReservedBooks);
    }

    public static Book defaultBook() {
        return defaultBookBuilder().build();
    }

    public static Book.BookBuilder defaultBookBuilder() {
        return Book.builder()
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
