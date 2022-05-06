package com.productdock.library.inventory.domain;

import com.productdock.library.inventory.book.BookInteraction;
import com.productdock.library.inventory.book.BookService;
import com.productdock.library.inventory.record.RentalStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;

import static com.productdock.library.inventory.data.provider.BookMother.defaultBook;
import static com.productdock.library.inventory.data.provider.BookMother.defaultBookEntity;
import static com.productdock.library.inventory.data.provider.RentalRecordMother.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
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

        book.processRentalRecords(rentalRecord);

        assertThat(book.getAvailableBooksCount()).isZero();
        assertThat(book.getRentedBooks()).isEqualTo(1);
    }
}
