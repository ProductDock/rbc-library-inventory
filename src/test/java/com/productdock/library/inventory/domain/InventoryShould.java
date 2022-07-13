package com.productdock.library.inventory.domain;

import org.junit.jupiter.api.Test;

import static com.productdock.library.inventory.data.provider.domain.InventoryMother.defaultInventory;
import static com.productdock.library.inventory.data.provider.domain.RentalRecordMother.defaultRentalRecord;
import static org.assertj.core.api.Assertions.assertThat;

class InventoryShould {

    @Test
    void getAvailableBookCount() {
        var inventory = defaultInventory();

        int availableBookCount = inventory.getAvailableBooksCount();

        assertThat(availableBookCount).isEqualTo(3);
    }

    @Test
    void processRentalRecords() {
        var inventory = defaultInventory();
        var rentalRecord = defaultRentalRecord();

        inventory.updateStateWith(rentalRecord);

        assertThat(inventory.getAvailableBooksCount()).isZero();
        assertThat(inventory.getRentedBooks()).isEqualTo(1);
        assertThat(inventory.getReservedBooks()).isEqualTo(2);
    }
}
