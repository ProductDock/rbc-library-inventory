package com.productdock.library.inventory.adapter.in.kafka.messages;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.productdock.library.inventory.data.provider.in.kafka.BookRentalStatusChangedMother.bookRentalStatusChanged;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BookRentalsMapperImpl.class})
class BookRentalsMapperShould {

    @Autowired
    private BookRentalsMapper bookRentalsMapper;

    @Test
    void mapMessageToDomain() {
        var bookRentalStatusChanged = bookRentalStatusChanged();

        var bookRentals = bookRentalsMapper.toDomain(bookRentalStatusChanged);

        assertThat(bookRentals.getBookId()).isEqualTo(bookRentalStatusChanged.bookId);
        assertThat(bookRentals.getBookCopiesRentalState()).hasSameSizeAs(bookRentalStatusChanged.rentalRecords);
    }

}
