package com.productdock.library.inventory.record;

import com.productdock.library.inventory.book.BookInteraction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;

import static com.productdock.library.inventory.data.provider.BookMother.defaultBook;
import static com.productdock.library.inventory.data.provider.RentalRecordMother.defaultRentalRecord;
import static com.productdock.library.inventory.data.provider.RentalRecordMother.defaultRentalRecordBuilder;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RentalRecordShould {

    @Test
    void getRentsCount_whenBookRentRecordExists() {
        var rentalRecord = defaultRentalRecord();

        int rentsCount = rentalRecord.getRentsCount();

        assertThat(rentsCount).isEqualTo(1);
    }

    @Test
    void getReservationsCount_whenBookReserveRecordExists() {
        var rentalRecord = defaultRentalRecordBuilder().rentalRecords(new ArrayList<>(Arrays.asList(new BookInteraction("defaultUserEmail", RentalStatus.RESERVED)))).build();

        int rentsCount = rentalRecord.getReservationsCount();

        assertThat(rentsCount).isEqualTo(1);
    }

}
