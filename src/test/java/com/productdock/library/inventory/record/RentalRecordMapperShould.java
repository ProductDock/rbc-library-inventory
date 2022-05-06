package com.productdock.library.inventory.record;

import com.productdock.library.inventory.book.BookInteraction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.productdock.library.inventory.data.provider.RentalRecordMother.defaultRentalRecordMessage;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RentalRecordMapperImpl.class})
class RentalRecordMapperShould {
    @Autowired
    private RentalRecordMapper rentalRecordMapper;

    @Test
    void mapBookToBookEntity() {
        var rentalRecordMessage = defaultRentalRecordMessage();

        var rentalRecord = rentalRecordMapper.toDomain(rentalRecordMessage);

        assertThat(rentalRecord.getBookId()).isEqualTo(rentalRecordMessage.getBookId());
        assertThatRecordsAreMatching(rentalRecordMessage.getRentalRecords(), rentalRecord.getRentalRecords());
    }

    private void assertThatRecordsAreMatching (List<RentalRecordMessage.RentalRecordRequest> bookMessageInteractions, List<BookInteraction> bookInteractions) {
        assertThat(bookMessageInteractions).hasSameSizeAs(bookInteractions);
        var bookMessageInteraction = bookMessageInteractions.get(0);
        var bookInteraction = bookInteractions.get(0);
        assertThatBookCopyIsMatching(bookMessageInteraction, bookInteraction);
    }

    private void assertThatBookCopyIsMatching(RentalRecordMessage.RentalRecordRequest bookCopy, BookInteraction bookInteraction) {
        assertThat(bookCopy.getPatron()).isEqualTo(bookInteraction.getPatron());
        assertThat(bookCopy.getStatus()).isEqualTo(bookInteraction.getStatus());
    }
}
