package com.productdock.library.inventory.domain;

import org.junit.jupiter.api.Test;

import static com.productdock.library.inventory.data.provider.domain.BookRentalsMother.bookRentals;
import static com.productdock.library.inventory.data.provider.domain.InventoryMother.inventory;
import static org.assertj.core.api.Assertions.assertThat;

class InventoryShould {

    @Test
    void getAvailableBookCount() {
        var inventory = inventory();

        int availableBookCount = inventory.getAvailableBooksCount();

        assertThat(availableBookCount).isEqualTo(3);
    }

    @Test
    void updateStateWith() {
        var inventory = inventory();
        var bookRentals = bookRentals();

        inventory.updateStateWith(bookRentals);

        assertThat(inventory.getAvailableBooksCount()).isZero();
        assertThat(inventory.getRentedBooks()).isEqualTo(1);
        assertThat(inventory.getReservedBooks()).isEqualTo(2);
    }
}
