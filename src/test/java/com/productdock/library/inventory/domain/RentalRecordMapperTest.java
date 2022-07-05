package com.productdock.library.inventory.domain;

import org.junit.jupiter.api.Test;

import static com.productdock.library.inventory.data.provider.in.kafka.RentalRecordMessageMother.defaultRentalRecordMessage;
import static org.assertj.core.api.Assertions.assertThat;

class RentalRecordMapperTest {

    RentalRecordMapper rentalRecordMapper = new RentalRecordMapper();

    @Test
    void mapBookToBookEntity() {
        var rentalRecordMessage = defaultRentalRecordMessage();

        var rentalRecord = rentalRecordMapper.toDomain(rentalRecordMessage);

        assertThat(rentalRecord.getBookId()).isEqualTo(rentalRecordMessage.getBookId());
        assertThat(rentalRecord.getRentsCount()).isEqualTo(1);
        assertThat(rentalRecord.getReservationsCount()).isZero();
    }

}
