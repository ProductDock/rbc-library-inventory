package com.productdock.library.inventory.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.productdock.library.inventory.data.provider.BookMother.defaultBook;
import static com.productdock.library.inventory.data.provider.RentalRecordMother.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookShould {

    @Test
    void getAvailableBookCount() {
        var book = defaultBook();

        int availableBookCount = book.getAvailableBooksCount();

        assertThat(availableBookCount).isEqualTo(1);
    }

    @Test
    void processRentalRecords() {
        var book = defaultBook();
        var rentalRecord = defaultRentalRecord();

        book.updateStateWith(rentalRecord);

        assertThat(book.getAvailableBooksCount()).isZero();
        assertThat(book.getRentedBooks()).isEqualTo(1);
    }
}
