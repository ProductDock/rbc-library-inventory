package com.productdock.library.inventory.data.provider.domain;

import com.productdock.library.inventory.domain.BookRentals;

import java.util.ArrayList;
import java.util.Arrays;

import static com.productdock.library.inventory.data.provider.domain.BookCopyRentalStateMother.rentedBookCopy;
import static com.productdock.library.inventory.data.provider.domain.BookCopyRentalStateMother.reservedBookCopy;

public class BookRentalsMother {

    private static final String defaultBookId = "1";

    public static BookRentals bookRentals() {
        return bookRentalsBuilder().build();
    }

    public static BookRentals.BookRentalsBuilder bookRentalsBuilder() {
        return BookRentals.builder()
                .bookId(defaultBookId)
                .bookCopiesRentalState(
                        new ArrayList<>(Arrays.asList(rentedBookCopy(), reservedBookCopy(), reservedBookCopy())));
    }
}
