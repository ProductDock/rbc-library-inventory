package com.productdock.library.inventory.data.provider.domain;

import com.productdock.library.inventory.domain.BookRentals;
import com.productdock.library.inventory.domain.RentalStatus;

public class BookCopyRentalStateMother {

    private static final String defaultPatron = "default@gmail.com";

    public static BookRentals.BookCopyRentalState rentedBookCopy() {
        return BookRentals.BookCopyRentalState.builder()
                .patron(defaultPatron)
                .status(RentalStatus.RENTED)
                .build();
    }

    public static BookRentals.BookCopyRentalState reservedBookCopy() {
        return reservedBookCopyBuilder().build();
    }

    public static BookRentals.BookCopyRentalState.BookCopyRentalStateBuilder reservedBookCopyBuilder() {
        return BookRentals.BookCopyRentalState.builder()
                .patron(defaultPatron)
                .status(RentalStatus.RESERVED);
    }
}
