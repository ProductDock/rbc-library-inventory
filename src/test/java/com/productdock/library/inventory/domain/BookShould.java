package com.productdock.library.inventory.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.productdock.library.inventory.data.provider.BookMother.defaultInventory;
import static com.productdock.library.inventory.data.provider.RentalRecordMother.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class BookShould {

    @Test
    void getAvailableBookCount() {
        var inventory = defaultInventory();

        int availableBookCount = inventory.getAvailableBooksCount();

        assertThat(availableBookCount).isEqualTo(1);
    }

    @Test
    void processRentalRecords() {
        var inventory = defaultInventory();
        var rentalRecord = defaultRentalRecord();

        inventory.updateStateWith(rentalRecord);

        assertThat(inventory.getAvailableBooksCount()).isZero();
        assertThat(inventory.getRentedBooks()).isEqualTo(1);
    }
}
