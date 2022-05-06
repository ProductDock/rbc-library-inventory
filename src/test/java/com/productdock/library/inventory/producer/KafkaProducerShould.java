package com.productdock.library.inventory.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.productdock.library.inventory.book.BookAvailabilityMessage;
import com.productdock.library.inventory.domain.Book;
import com.productdock.library.inventory.record.RentalRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.productdock.library.inventory.data.provider.BookMother.defaultBookAvailabilityMessage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class KafkaProducerShould {

    @InjectMocks
    private RecordProducer recordProducer;

    private final String topic = "test-book-availability";

    @Test
    void produceCorrectMessage() throws JsonProcessingException {
        var bookAvailabilityMessage = defaultBookAvailabilityMessage();
        var producerRecord = recordProducer.createKafkaRecord(topic, bookAvailabilityMessage);
        Gson gson = new Gson();
        String desiredValue = "{\"bookId\":\"" + bookAvailabilityMessage.getBookId() + "\",\"availableBookCount\":" + bookAvailabilityMessage.getAvailableBookCount() + "}";

        assertThat(producerRecord.value()).isEqualTo(desiredValue);
    }

    @Test
    void throwExceptionWhenProducingBadEntity() {
        BookAvailabilityMessage bookAvailabilityMessage = mock(BookAvailabilityMessage.class);
        assertThrows(JsonProcessingException.class, () -> {
            var producerRecord = recordProducer.createKafkaRecord(topic, bookAvailabilityMessage);
        });
    }
}
